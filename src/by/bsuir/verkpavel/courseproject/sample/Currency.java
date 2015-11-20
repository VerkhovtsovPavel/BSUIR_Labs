package by.bsuir.verkpavel.courseproject.sample;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Example account object that is persisted to disk by the DAO and other example classes.
 */
@DatabaseTable(tableName = "currency")
public class Currency {

        // for QueryBuilder to be able to find the fields
        public static final String CURRENCY_FIELD_NAME = "description";

        @DatabaseField(generatedId = true)
        private int id;

        @DatabaseField(columnName = CURRENCY_FIELD_NAME, canBeNull = false)
        private String description;

        Currency() {
                // all persisted classes must define a no-arg constructor with at least package visibility
        }

        public Currency(String name) {
                this.description = name;
        }


        public int getId() {
                return id;
        }

        public String getName() {
                return description;
        }

        public void setName(String name) {
                this.description = name;
        }

        @Override
        public int hashCode() {
                return description.hashCode();
        }

        @Override
        public boolean equals(Object other) {
                if (other == null || other.getClass() != getClass()) {
                        return false;
                }
                return description.equals(((Currency) other).description);
        }
}

