# Database lock

This repo is a sample to show two possible uses for lock database using `Postgres`

## Docker postgres

```shell
docker run --rm --name local-postgres -p 5432:5432 -e POSTGRES_USER=databaselock -e POSTGRES_PASSWORD=databaselock -d postgres
```

---
## Reference
- https://twitter.com/rponte/status/1577657523448020992
- https://github.com/rafaelpontezup/preventing-lost-update-racecondition/blob/main/src/main/java/br/com/stackspot/nullbank/shared/lockmanager/PostgresLockManager.java