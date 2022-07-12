# Java And Reactive(JAR) Stack

A Drone pipeline JAR(Java And Reactive) stack to show build, test and deploy a [Java](https://jdk.java.net/) API with [React](https://reactjs.org/) Frontend with optional DB. 

The stack has the following components,

- A [Quarkus](https://quarkus.io) based Java REST API with optional persistence using Hibernate
- An React based UI application

## Pre-requisites

- [Docker Desktop for Mac/Windows](https://www.docker.com/products/docker-desktop/) and Docker on Linux

- [Drone CLI](https://docs.drone.io/cli)
  
- [Google Cloud](https://cloud.google.com/) Service Account(SA) with permissions to,
  - Ability to deploy to [Google Cloud Run](https://cloud.google.com/run)
  - Ability to push to [Google Cloud Registry](https://cloud.google.com/container-registry/)
  
- Optionally [gcloud CLI](https://cloud.google.com/cli)

## API Access

### Make Service publicly accessible

As the API is not enabled with authentication by default to quickly test the application try allowing `allUsers` to access the API,

```shell
gcloud run services add-iam-policy-binding fruits-api \
  --region="${GCP_REGION}" \
  --member="allUsers" \
  --role="roles/run.invoker"
```

### Disable Public Access to API

To switch back to authentication mode use the following command,

```shell
gcloud run services remove-iam-policy-binding fruits-api --member='allUsers' --role='roles/run.invoker' --region="${GCP_REGION}"
```

## Build and Deploy Application

Create the secret file

```shell
cp secret.example my.secret
```

Edit the `my.secret` and update the value to suit your environment and settings.

```shell
drone exec --secret=my.secret .drone.yml
```

The Drone pipeline command will now start to build the Java Application, push the built application to image registry (Google Container Registry), Deploy the container image as Google Cloud Run service and finally push the UI application Vercel.
