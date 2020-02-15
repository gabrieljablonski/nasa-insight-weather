from json import dumps
from requests import get
from random import randint

NASA_API_PREFIX = 'https://api.nasa.gov'
INSIGHT_WEATHER_ENDPOINT = '/insight_weather'
DEFAULT_API_KEY = 'DEMO_KEY'

DEFAULT_PORT = 8080
LOCAL_API_ENDPOINT = 'http://localhost:{port}/nasa/temperature'


def call_local_api(port, sol=None, force_cache_expire=False):
    parameters = f"?force_cache_expire={force_cache_expire}"
    if sol:
        parameters += f"&sol={sol}"

    uri = f"{LOCAL_API_ENDPOINT.format(port=port)}{parameters}"
    response = get(uri)

    if response.status_code == 200:
        return response.json()
    elif response.status_code == 404:
        return {'status_code': 404}
    raise Exception('failed to call local api')


def call_nasa_api(api_key):
    parameters = f"?api_key={api_key}&feedtype=json&ver=1.0"

    uri = f"{NASA_API_PREFIX}{INSIGHT_WEATHER_ENDPOINT}/{parameters}"
    response = get(uri)

    if response.status_code == 200:
        return response.json()
    raise Exception('failed to call nasa api')


def test_with_no_sol(nasa_data, port):
    local_data = call_local_api(port)
    print('\nLocal data:')
    print(dumps(local_data))
    del nasa_data['sol_keys']
    sum_temperature = 0
    sample_count = 0
    for key, val in nasa_data.items():
        at = val.get('AT')
        if at:
            av = at.get('av')
            if av:
                sum_temperature += float(av)
                sample_count += 1
    nasa_average_temperature = float(f"{sum_temperature/sample_count:.3f}")
    local_average_temperature = local_data.get('average_temperature')

    print('\n--Calculated average temperatures:')
    print(f"NASA: {nasa_average_temperature:.3f}")
    print(f"Local: {local_average_temperature:.3f}")
    assert nasa_average_temperature == local_average_temperature, 'Average temperature mismatch\n'

def test_with_sol(nasa_data, port):
    sol_keys = nasa_data.get('sol_keys')

    for key in sol_keys:
        print('-'*30)
        print(f"Testing for sol {key}")
        sol_data = nasa_data.get(key)
        nasa_average_temperature = None
        if sol_data:
            at = sol_data.get('AT')
            if at and at.get('av'):
                nasa_average_temperature = float(at.get('av'))
        
        local_data = call_local_api(port, sol=key)
        print('\nLocal data:')
        print(dumps(local_data))
        local_average_temperature = None
        if local_data.get('average_temperature'):
            local_average_temperature = float(local_data.get('average_temperature'))
        
        print('\n--Retrieved average temperatures:')
        print(f"NASA: {nasa_average_temperature}")
        print(f"Local: {local_average_temperature}")
        print('-'*30)

        assert nasa_average_temperature == local_average_temperature, 'Average temperature mismatch\n'


def test_with_invalid_sol(port):
    random_invalid_sol = randint(-10, -1)
    print(f"\nTesting invalid sol {random_invalid_sol}")
    local_data = call_local_api(port, sol=random_invalid_sol)
    print('\nLocal data:')
    print(dumps(local_data))
    assert local_data.get('status_code') == 405, 'Expected 404 Not Found error\n'



def main(port, api_key):
    nasa_data = call_nasa_api(api_key)
    del nasa_data['validity_checks']
    print('NASA data:')
    print(dumps(nasa_data))

    success = [True, True, True]
    try:
        # test with no specified sol
        print()
        print('-'*50)
        print('Testing average temperature for whole week')
        test_with_no_sol(nasa_data.copy(), port)
        print('\n==Test passed')
        print('-'*50)
    except AssertionError as e:
        success[0] = False
        print(e)

    try:
        # test with specified sol
        print()
        print('-'*50)
        print('Testing average temperature for each sol available')
        test_with_sol(nasa_data.copy(), port)
        print('\n==Test passed')
        print('-'*50)
    except AssertionError as e:
        success[1] = False
        print(e)

    try:
        # test with invalid sol
        print()
        print('-'*50)
        print('Testing request with invalid sol')
        test_with_invalid_sol(port)
        print('\n==Test passed')
        print('-'*50)
    except AssertionError as e:
        success[2] = False
        print(e)
    
    if all(success):
        print('All tests were successful!\n\n')
    else:
        print('Not all tests were successful.')

if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser(description='Test the NASA API wrapper.')
    parser.add_argument('--port', type=int, default=DEFAULT_PORT, help='Port where local API is running')
    parser.add_argument('--api-key', type=str, default=DEFAULT_API_KEY, help='API key for NASA API calls')

    args = parser.parse_args()
    main(args.port, args.api_key)
