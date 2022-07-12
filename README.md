# Java, H2 and React Stack

A Drone pipeline example stack to show build, test and deploy a Java, NodeJS and H2 db application. The stack has the following components,

- A Java REST API called Fruits API running in serverless mode on Google Cloud Run
- The persistence using Hibernate defaults H2
- An React UI application deployed to Vercel

## Make Service publicly accessible

```shell
gcloud run services add-iam-policy-binding fruits-api \
  --region="${GCP_REGION}" \
  --member="allUsers" \
  --role="roles/run.invoker"
```
