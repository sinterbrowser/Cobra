/*
 *
 * @package com.lobobrowser.cobra.data.db.tables
 * @license http://lobobrowser.com/about/license
 * @copyright (c) 2015 Lobo Browser Team
 *
 */
package com.lobobrowser.cobra.data.db.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.5.0"
    },
    comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Permissions extends org.jooq.impl.TableImpl<com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord> {

  private static final long serialVersionUID = -1001981162;

  /**
   * The reference instance of <code>PUBLIC.PERMISSIONS</code>
   */
  public static final com.lobobrowser.cobra.data.db.tables.Permissions PERMISSIONS = new info.gngr.db.tables.Permissions();

  /**
   * The class holding records for this type
   */
  @Override
  public java.lang.Class<com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord> getRecordType() {
    return com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord.class;
  }

  /**
   * The column <code>PUBLIC.PERMISSIONS.FRAMEHOST</code>.
   */
  public final org.jooq.TableField<com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord, java.lang.String> FRAMEHOST = createField("FRAMEHOST",
      org.jooq.impl.SQLDataType.VARCHAR.length(2147483647).nullable(false), this, "");

  /**
   * The column <code>PUBLIC.PERMISSIONS.REQUESTHOST</code>.
   */
  public final org.jooq.TableField<com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord, java.lang.String> REQUESTHOST = createField(
      "REQUESTHOST", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647).nullable(false), this, "");

  /**
   * The column <code>PUBLIC.PERMISSIONS.PERMISSIONS</code>.
   */
  public final org.jooq.TableField<com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord, java.lang.Integer> PERMISSIONS_ = createField(
      "PERMISSIONS", org.jooq.impl.SQLDataType.INTEGER, this, "");

  /**
   * Create a <code>PUBLIC.PERMISSIONS</code> table reference
   */
  public Permissions() {
    this("PERMISSIONS", null);
  }

  /**
   * Create an aliased <code>PUBLIC.PERMISSIONS</code> table reference
   */
  public Permissions(final java.lang.String alias) {
    this(alias, com.lobobrowser.cobra.data.db.tables.Permissions.PERMISSIONS);
  }

  private Permissions(final java.lang.String alias, final org.jooq.Table<com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord> aliased) {
    this(alias, aliased, null);
  }

  private Permissions(final java.lang.String alias, final org.jooq.Table<com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord> aliased,
      final org.jooq.Field<?>[] parameters) {
    super(alias, com.lobobrowser.cobra.data.db.Public.PUBLIC, aliased, parameters, "");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public org.jooq.UniqueKey<com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord> getPrimaryKey() {
    return com.lobobrowser.cobra.data.db.Keys.CONSTRAINT_C;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public java.util.List<org.jooq.UniqueKey<com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord>> getKeys() {
    return java.util.Arrays.<org.jooq.UniqueKey<com.lobobrowser.cobra.data.db.tables.records.PermissionsRecord>> asList(com.lobobrowser.cobra.data.db.Keys.CONSTRAINT_C);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public com.lobobrowser.cobra.data.db.tables.Permissions as(final java.lang.String alias) {
    return new com.lobobrowser.cobra.data.db.tables.Permissions(alias, this);
  }

  /**
   * Rename this table
   */
  public com.lobobrowser.cobra.data.db.tables.Permissions rename(final java.lang.String name) {
    return new com.lobobrowser.cobra.data.db.tables.Permissions(name, null);
  }
}