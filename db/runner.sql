#"C:\Program Files (x86)\MySQL\MySQL Server 5.6\bin\mysql.exe" "--defaults-file=C:\ProgramData\MySQL\MySQL Server 5.6\my.ini" -uroot -p123456 --default-character-set=utf8 < runner.sql --verbose

use deliveryService;

source ./schema.sql;

source data_scripts/cityStreet.sql;
source data_scripts/rate.sql;
source data_scripts/position.sql;
source data_scripts/permissions.sql;
source data_scripts/markParcel.sql;
source data_scripts/authentication.sql;
source data_scripts/driverLicenceCategory.sql;
source data_scripts/paymentsSystemType.sql;
source data_scripts/deliveryStatus.sql;

source data_scripts/client.sql;
source data_scripts/office.sql;

source data_scripts/salary.sql;
source data_scripts/corporateCar.sql;
source data_scripts/employee.sql;
source data_scripts/driverlicense.sql;
source data_scripts/driver.sql;

source data_scripts/payments.sql;
source data_scripts/parcel.sql;
source data_scripts/delivery.sql;
source data_scripts/parcel_m2m_delivery.sql;