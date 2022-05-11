# Time is tricky.
## Especially with serialization.

This mini project aims to be a sandbox with a custom Jackson configuration, to track down a problem I ran into in a
production project.

There is one endpoint:

```shell
> http :8080/time/current

HTTP/1.1 200 OK
Content-Length: 37
Content-Type: application/json

{
    "time": "2022-05-11T01:28:08.57855Z"
}

```

It returns the current time in a standard format ISO-8601.

That's it. That's all this does. Sorry you had to see it.