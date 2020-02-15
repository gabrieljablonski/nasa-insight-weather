# NASA API Java wrapper

Java wrapper for NASA's [InSight: Mars Weather Service API](https://api.nasa.gov/assets/insight/InSight%20Weather%20API%20Documentation.pdf).

Based on the [Spring Boot RESTful web service](https://spring.io/guides/gs/rest-service/).

## Functionality

### Requests

The API offers only one endpoint: `/nasa/temperature`, which can be called to obtain the average Mars temperature using the data provided by NASA's API.

Two optional parameters are available:

- `sol` - It can be used to specify the sol to retrieve the average temperature. If data for the sol is not available, `404 Not Found` error is returned. If not specified, the average for all available sols (usually past 7 days) is returned.

- `force_cache_expire` - Forces the API to clear any cached data before serving the request.

Example call for sol 430, with local API running on `localhost:8080`:

```sh
    GET http://localhost:8080/nasa/temperature?sol=430&force_cache_expire=true
```

### Responses

The responses are formatted as JSON. On a successful request, the response contains the following fields:

- `status_code` - Status code for the HTTP request (usually `200`).
- `message` - Additional information regarding the response data.
- `average_temperature` - Average temperature retrieved. Rounded to 3 decimal places.
- `sol_keys` - The sols used for calculating the average temperature.

On a failed request, the response contains the following fields:

- `status_code` - Either `404` for unavailable sol, or `500` for unexpected errors.
- `error` - Error name.
- `message` - Error description.

Example for successful request for sol 430:

```json
{ 
    "sol_keys": [430],
    "average_temperature": -61.245,
    "status_code": 200,
    "message": "Request successful."
}
```

Example for successful request with no specified sol:

```json
{ 
    "sol_keys": [427, 428, 429, 430, 431, 432, 433],
    "average_temperature": -60.886,
    "status_code": 200,
    "message": "Request successful."
}
```

Example for failed request for unavailable sol:

```json
{ 
    "error": "NotFound",
    "status_code": 404,
    "message": "Sol key not found. Available sol keys: [427, 428, 429, 430, 431, 432, 433]."
}
```

## Implementation Details

All of the top-level logic takes place inside the API controller [`com.nasa.api.NasaApiController`](/src/main/java/com/nasa/api/NasaApiController.java).

Interfacing with the NASA API is done by [`com.nasa.api.InSightWeatherApi`](/src/main/java/com/nasa/api/InSightWeatherApi.java).

The models and JSON deserialization for handling the incoming data from NASA's API are implemented by the packages [`com.nasa.api.model`](/src/main/java/com/nasa/api/model) and [`com.nasa.api.deserialization`](/src/main/java/com/nasa/api/deserialization), respectively.

The data handling for interfacing with users of the API is implemented on the [`com.nasa.api.response`](/src/main/java/com/nasa/api/response) package.

This implementation also offers basic in-memory caching features ([`com.nasa.api.Cache`](/src/main/java/com/nasa/api/Cache.java)), which means not all calls to this API cause a request to be sent to NASA's API, improving performance.

## Running

soon™

## Automatized Testing

A testing script ([`automatic_testing.py`](/automatic_testing.py)) is available to test the three different types of responses: 

- No specified sol
- Sol specified and available
- Sol specified but unavailable

The script requires Python 3.6+.

To run it, first change into the project directory and setup a virtual environment.

On Linux:
```sh
    cd /nasa-api
    python3 -m venv venv
    source ./venv/bin/activate
    python3 -m pip install -r requirements.txt
```

On Windows:
```ps
    cd .\nasa-api
    python -m venv venv
    .\venv\Scripts\activate
    python -m pip install -r requirements.txt
```

The script offers two optional command line arguments:

- `port` - The port in which the local API is running (defaults to `8080`)
- `api-key` - The API key for making NASA API calls (defaults to `DEMO_KEY`)

Running on Linux:

```sh
    python3 ./automatic_testing.py --port 8080 --api-key DEMO_KEY
```

On Windows:

```ps
    python .\automatic_testing.py --port 8080 --api-key DEMO_KEY
```