package com.springmvc.util;

import org.apache.ibatis.jdbc.SQL;

public class ConstructorSqlUtil {
    /**
     * String sql = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME, "
     * "P.LAST_NAME,P.CREATED_ON, P.UPDATED_ON " +
     * "FROM PERSON P, ACCOUNT A " +
     * "INNER JOIN DEPARTMENT D on D.ID = P.DEPARTMENT_ID " +
     * "INNER JOIN COMPANY C on D.COMPANY_ID = C.ID " +
     * "WHERE (P.ID = A.ID AND P.FIRST_NAME like ?) " +
     * "OR (P.LAST_NAME like ?) " +
     * "GROUP BY P.ID " +
     * "HAVING (P.LAST_NAME like ?) " +
     * "OR (P.FIRST_NAME like ?) " +
     * "ORDER BY P.ID, P.FULL_NAME";
     *
     * @return
     */
    private String selectPersonSql() {
        return new SQL() {{
            SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
            FROM("PERSON P");
            FROM("ACCOUNT A");
            INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
            INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
            WHERE("P.ID = A.ID");
            WHERE("P.FIRST_NAME like ?");
            OR();
            WHERE("P.LAST_NAME like ?");
            GROUP_BY("P.ID");
            HAVING("P.LAST_NAME like ?");
            OR();
            HAVING("P.FIRST_NAME like ?");
            ORDER_BY("P.ID");
            ORDER_BY("P.FULL_NAME");
        }}.toString();
    }

    public String deletePersonSql() {
        return new SQL() {{
            DELETE_FROM("PERSON");
            WHERE("ID = #{id}");
        }}.toString();
    }

    public String updatePersonSql() {
        return new SQL() {{
            UPDATE("PERSON");
            SET("FIRST_NAME = #{firstName}");
            WHERE("ID = #{id}");
        }}.toString();
    }

    public String insertPersonSql() {
        String sql = new SQL()
                .INSERT_INTO("PERSON")
                .VALUES("ID, FIRST_NAME", "#{id}, #{firstName}")
                .VALUES("LAST_NAME", "#{lastName}")
                .toString();
        return sql;
    }

    // With conditionals (note the final parameters, required for the anonymous inner class to access them)
    public String selectPersonLike(final String id, final String firstName, final String lastName) {
        return new SQL() {{
            SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME");
            FROM("PERSON P");
            if (id != null) {
                WHERE("P.ID like #{id}");
            }
            if (firstName != null) {
                WHERE("P.FIRST_NAME like #{firstName}");
            }
            if (lastName != null) {
                WHERE("P.LAST_NAME like #{lastName}");
            }
            ORDER_BY("P.LAST_NAME");
        }}.toString();
    }

    /**
     * SELECT(String)
     * 开始或插入到 SELECT子句。 可以被多次调用，参数也会添加到 SELECT子句。
     * 参数通常使用逗号分隔的列名和别名列表，但也可以是数据库驱动程序接受的任意类型。
     * <p>
     * SELECT_DISTINCT(String)
     * 开始或插入到 SELECT子句， 也可以插入 DISTINCT关键字到生成的查询语句中。 可以被多次调用，参数也会添加到 SELECT子句。
     * 参数通常使用逗号分隔的列名和别名列表，但也可以是数据库驱动程序接受的任意类型。
     * <p>
     * FROM(String)
     * 开始或插入到 FROM子句。 可以被多次调用，参数也会添加到 FROM子句。 参数通常是表名或别名，也可以是数据库驱动程序接受的任意类型。
     * <p>
     * JOIN(String) INNER_JOIN(String) LEFT_OUTER_JOIN(String) RIGHT_OUTER_JOIN(String)
     * 基于调用的方法，添加新的合适类型的 JOIN子句。 参数可以包含由列命和join on条件组合成标准的join。
     * <p>
     * WHERE(String)
     * 插入新的 WHERE子句条件， 由AND链接。可以多次被调用，每次都由AND来链接新条件。使用 OR() 来分隔OR。
     * <p>
     * OR()
     * 使用OR来分隔当前的 WHERE子句条件。 可以被多次调用，但在一行中多次调用或生成不稳定的SQL。
     * <p>
     * AND()
     * 使用AND来分隔当前的 WHERE子句条件。 可以被多次调用，但在一行中多次调用或生成不稳定的SQL。
     * 因为 WHERE 和 HAVING 二者都会自动链接 AND, 这是非常罕见的方法，只是为了完整性才被使用。
     * <p>
     * GROUP_BY(String)
     * 插入新的 GROUP BY子句元素，由逗号连接。 可以被多次调用，每次都由逗号连接新的条件。
     * <p>
     * HAVING(String)
     * 插入新的 HAVING子句条件。 由AND连接。可以被多次调用，每次都由AND来连接新的条件。使用 OR() 来分隔OR.
     * <p>
     * ORDER_BY(String)
     * 插入新的 ORDER BY子句元素， 由逗号连接。可以多次被调用，每次由逗号连接新的条件
     * <p>
     * LIMIT(String)
     * 附加一个限制条款。此方法与SELECT（）、UPDATE（）和DELETE（）一起使用时有效。
     * 此方法设计为在使用SELECT（）时与OFFSET（）一起使用。（从3.5.2开始提供）
     * <p>
     * OFFSET(String)
     * 附加偏移子句。此方法与SELECT（）一起使用时有效。此方法与LIMIT（）一起使用。（从3.5.2开始提供）
     * <p>
     * OFFSET_ROWS(String)
     * 追加一个OFFSET n ROWS子句。此方法与SELECT（）一起使用时有效。此方法设计为与FETCH_FIRST_ROWS_ONLY（）一起使用。（从3.5.2开始提供）
     * <p>
     * FETCH_FIRST_ROWS_ONLY(String)
     * 追加一个FETCH FIRST n ROWS ONLY子句。此方法与SELECT（）一起使用时有效。此方法设计用于与OFFSET_ROWS（）一起使用。（从3.5.2开始提供）
     * <p>
     * DELETE_FROM(String)
     * 开始一个delete语句并指定需要从哪个表删除的表名。通常它后面都会跟着WHERE语句！
     * <p>
     * INSERT_INTO(String)
     * 始一个insert语句并指定需要插入数据的表名。后面都会跟着一个或者多个VALUES() or INTO_COLUMNS() and INTO_VALUES()。
     * <p>
     * VALUES(String, String)
     * 插入到insert语句中。第一个参数是要插入的列名，第二个参数则是该列的值。
     * <p>
     * UPDATE(String)
     * 开始一个update语句并指定需要更新的表明。后面都会跟着一个或者多个SET()，通常也会有一个WHERE()。
     * <p>
     * SET(String)
     * 针对update语句，插入到"set"列表中
     * <p>
     * INTO_COLUMNS(String...)
     * 将列短语附加到insert语句。这应该一起调用到_VALUES（）中。
     * <p>
     * INTO_VALUES(String...)
     * 将值短语附加到insert语句。这应该与一起调用到_COLUMNS（）中。
     * <p>
     * ADD_ROW()
     * 为批量插入添加新行。（从3.5.2开始提供）
     */

    public String selectPersonSqlNew() {
        return new SQL()
                .SELECT("P.ID", "A.USERNAME", "A.PASSWORD", "P.FULL_NAME", "D.DEPARTMENT_NAME", "C.COMPANY_NAME")
                .FROM("PERSON P", "ACCOUNT A")
                .INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID", "COMPANY C on D.COMPANY_ID = C.ID")
                .WHERE("P.ID = A.ID", "P.FULL_NAME like #{name}")
                .ORDER_BY("P.ID", "P.FULL_NAME")
                .toString();
    }

    public String insertPersonSqlNew() {
        return new SQL()
                .INSERT_INTO("PERSON")
                .INTO_COLUMNS("ID", "FULL_NAME")
                .INTO_VALUES("#{id}", "#{fullName}")
                .toString();
    }

    public String updatePersonSqlNew() {
        return new SQL()
                .UPDATE("PERSON")
                .SET("FULL_NAME = #{fullName}", "DATE_OF_BIRTH = #{dateOfBirth}")
                .WHERE("ID = #{id}")
                .toString();
    }

    public String insertPersonsSql() {
        // INSERT INTO PERSON (ID, FULL_NAME)
        //     VALUES (#{mainPerson.id}, #{mainPerson.fullName}) , (#{subPerson.id}, #{subPerson.fullName})
        return new SQL()
                .INSERT_INTO("PERSON")
                .INTO_COLUMNS("ID", "FULL_NAME")
                .INTO_VALUES("#{mainPerson.id}", "#{mainPerson.fullName}")
                .ADD_ROW()
                .INTO_VALUES("#{subPerson.id}", "#{subPerson.fullName}")
                .toString();
    }

    /**
     * limit y 分句表示: 读取 y 条数据
     * <p>
     * limit x, y 分句表示: 跳过 x 条数据，读取 y 条数据
     * <p>
     * limit y offset x 分句表示: 跳过 x 条数据，读取 y 条数据
     *
     * @return
     */
    public String selectPersonsWithOffsetLimitSql() {
        // SELECT id, name FROM PERSON
        //     LIMIT #{limit} OFFSET #{offset}
        return new SQL()
                .SELECT("id", "name")
                .FROM("PERSON")
                .LIMIT("#{limit}")
                .OFFSET("#{offset}")
                .toString();
    }

    /**
     * @return
     */
    public String selectPersonsWithFetchFirstSql() {
        // SELECT id, name FROM PERSON
        //     OFFSET #{offset} ROWS FETCH FIRST #{limit} ROWS ONLY
        return new SQL()
                .SELECT("id", "name")
                .FROM("PERSON")
                .OFFSET_ROWS("#{offset}")
                .FETCH_FIRST_ROWS_ONLY("#{limit}")
                .toString();
    }
}
