# Project CICD Pipeline Demo

This repository is to demonstrate my project's CI/CD pipeline.  
This is a simplified example; the real thing integrates with several pipelines.

## :pushpin: CI/CD Flow

<p align="center"><img width="712" height="650" alt="Image" src="https://github.com/user-attachments/assets/7febbd38-c0e4-4cd6-8d03-972489244126" /></p>

Here’s how my project's CI/CD process **actually** runs:

1. A push triggers the pipeline.
2. It dispatches an event to the DB management repo.
3. The DB repo validates the database DDL with Flyway.
4. Once validation passes, it dispatches an event back to the project repo.
5. The pipeline runs tests.
6. It builds the project, creates a Docker image, and pushes it to ECR.
7. It commits the new image tag to the Helm chart repo.
8. Argo CD polls the chart repo and detects the change.
9. Argo CD syncs to the new chart version.
10. k8s runs rollout deployment.

## :pushpin: Github Actions

- [github workflow example file](https://github.com/copebble/prayerhouse-cicd-demo/blob/main/.github/workflows/deploy-example.yaml)
- [this repository's workflows](https://github.com/copebble/prayerhouse-cicd-demo/actions/workflows/deploy-example.yaml)

### Dispatch event

```yaml
uses: peter-evans/repository-dispatch@v4
```
- [repository-dispatch github](https://github.com/peter-evans/repository-dispatch)

```yaml
steps:
  #...
  - name: Dispatch DB migration validate event
    uses: peter-evans/repository-dispatch@v4
    with:
      token: ${{ secrets.REPO_TOKEN }}
      repository: copebble/[TARGET_REPO_NAME]
      event-type: dispatch-event
      client-payload: |
        {
          "deploy_tag": "${{ env.TAG_NAME }}",
          #...
        }
  #...
```
dispatch event to the target repository.

```yaml
# receiver
on:
  repository_dispatch:
    types: [ dispatch-event ]

env:
  # the receiver can use the client-payload.
  DEPLOY_TAG: "${{ github.event.client_payload.deploy_tag }}"
```
The receiver, which detects the dispatch event, trigger the workflow.  
During the process, it can use the payloads which are sent from dispatcher workflow.