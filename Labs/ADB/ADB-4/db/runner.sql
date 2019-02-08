#mysql -u root -p --verbose < runner.sql
use bank_users;
source db_schema.sql;
source data_clients.sql;
source data_deposit.sql;
source data_credit.sql;
source data_account.sql;
source prepareToPlasticCard.sql
source plasticCardAndOrganization.sql
source settings.sql
