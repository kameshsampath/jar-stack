#!/bin/bash

set -e

set -o pipefail

IMAGE_NAME=${1:-ghcr.io/kameshsampath/fruits-app-ui}

docker build --no-cache --rm -t "$IMAGE_NAME" .
