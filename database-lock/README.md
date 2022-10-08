# Database lock

This repo is a sample to show two possible uses for lock database using `Postgres`
- Update: Updating has a pessimitic lock with timeout. If after certain time the lock can not acquire, we throw an exception. At least, we have a little time waiting the lock been released.
- Job: When we execute a job that is necessary to guarantee the execution only in one instance, we can use this approach too, in this case without waiting

## Docker postgres

```shell
docker run --rm --name local-postgres -p 5432:5432 -e POSTGRES_USER=databaselock -e POSTGRES_PASSWORD=databaselock -d postgres
```

---
## Reference
- https://twitter.com/rponte/status/1577657523448020992
- https://github.com/rafaelpontezup/preventing-lost-update-racecondition/blob/main/src/main/java/br/com/stackspot/nullbank/shared/lockmanager/PostgresLockManager.java