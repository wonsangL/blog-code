### Stored Procedure
```sql
CREATE DEFINER=`root`@`localhost` PROCEDURE `player_select`(
	i_id int
)
BEGIN
	select sleep(5);
	select * from player where id = i_id;
END
```
