# Running with Docker

This guide will help you set up and run the Coaching Service using Docker.

1. Install [Docker CLI](https://www.docker.com/products/cli/)*,
   or [Rancher Desktop](https://docs.rancherdesktop.io/getting-started/installation/).
2. Run `docker compose up` in the root directory of the repository. This will start the Coaching
   Service and all its dependencies.
3. Access the Coaching Service at [http://localhost:8080/](http://localhost:8080).
4. The API documentation is available
   at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

It's really that simple!

To destroy the resources once you've disconnected from the session, run `docker compose down` in the
root directory of the repository.

---

Docker build . --tag coaching-service


---

> \*Note: Due to the size of our company, we cannot use Docker Desktop. Instead, we use Rancher
> Desktop, which provides a similar
> experience.
>
> If you choose to use Rancher Desktop, ensure that you
> have set the container runtime to Docker in the Rancher Desktop settings.
>
> The Docker CLI tools are fine, if you'd like to use those.
