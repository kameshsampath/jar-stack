SECRET_FILE = secret.harness

app-deploy:	## Runs Drone Pipeline using drone exec and deploys the API to Google Cloud Run and UI to Vercel
	@drone exec --secret-file=$(SECRET_FILE) .drone.yml

ui-deploy:	## Deploys site to Vercel
	@drone exec --secret-file=$(SECRET_FILE) --include=deploy-ui .drone.yml

java-build-and-test:	## Just runs the Java build and test steps
	@drone exec --secret-file=$(SECRET_FILE) --include=java-test --include=java-build .drone.yml

help: ## Show this help
	@echo Please specify a build target. The choices are:
	@grep -E '^[0-9a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "$(INFO_COLOR)%-30s$(NO_COLOR) %s\n", $$1, $$2}'

.PHONY:	run