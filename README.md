# Getting Started


# Create Namespace for the Application

```shell script
kubectl apply -f k8s/namespace.yaml
```

## Deploy Database

```shell script
kubectl apply -k k8s/db/
```

__NOTE__: Make a note of the NodePort of the db

## Deploy API Registry

```shell script
kubectl apply -k k8s/apicurio/
```

__NOTE__: Make a note of the NodePort of the apicurio

## Deploy Kafka

```shell script
kubectl apply -k k8s/kafka/
```

## Build Debezium Connector Image

```shell script
docker build -t <your repo> -f k8s/debezium/Dockefile  k8s/debezium/
```

__NOTE__: Update the `<your-repo>` in the file k8s/debezium/debezium-connect.yaml#image