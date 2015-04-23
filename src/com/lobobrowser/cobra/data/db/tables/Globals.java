/*
 *
 * @package com.lobobrowser.cobra.data.db.tables.records
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
public class Globals extends org.jooq.impl.TableImpl<com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord> {

  private static final long serialVersionUID = 418901589;

  /**
   * The reference instance of <code>PUBLIC.GLOBALS</code>
   */
  public static final com.lobobrowser.cobra.data.db.tables.Globals GLOBALS = new com.lobobrowser.cobra.data.db.tables.Globals();

  /**
   * The class holding records for this type
   */
  @Override
  public java.lang.Class<com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord> getRecordType() {
    return com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord.class;
  }

  /**
   * The column <code>PUBLIC.GLOBALS.NOTACOLUMN</code>.
   */
  public final org.jooq.TableField<com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord, java.lang.String> NOTACOLUMN = createField("NOTACOLUMN",
      org.jooq.impl.SQLDataType.CHAR.nullable(false), this, "");

  /**
   * The column <code>PUBLIC.GLOBALS.SCHEMAVERSION</code>.
   */
  public final org.jooq.TableField<com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord, java.lang.Integer> SCHEMAVERSION = createField(
      "SCHEMAVERSION", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

  /**
   * The column <code>PUBLIC.GLOBALS.PERMISSIONSINITIALIZED</code>.
   */
  public final org.jooq.TableField<com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord, java.lang.Boolean> PERMISSIONSINITIALIZED = createField(
      "PERMISSIONSINITIALIZED", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaulted(true), this, "");

  /**
   * Create a <code>PUBLIC.GLOBALS</code> table reference
   */
  public Globals() {
    this("GLOBALS", null);
  }

  /**
   * Create an aliased <code>PUBLIC.GLOBALS</code> table reference
   */
  public Globals(final java.lang.String alias) {
    this(alias, com.lobobrowser.cobra.data.db.tables.Globals.GLOBALS);
  }

  private Globals(final java.lang.String alias, final org.jooq.Table<com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord> aliased) {
    this(alias, aliased, null);
  }

  private Globals(final java.lang.String alias, final org.jooq.Table<com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord> aliased,
      final org.jooq.Field<?>[] parameters) {
    super(alias, com.lobobrowser.cobra.data.db.Public.PUBLIC, aliased, parameters, "");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public org.jooq.UniqueKey<com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord> getPrimaryKey() {
    return com.lobobrowser.cobra.data.db.Keys.CONSTRAINT_3;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public java.util.List<org.jooq.UniqueKey<com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord>> getKeys() {
    return java.util.Arrays.<org.jooq.UniqueKey<com.lobobrowser.cobra.data.db.tables.records.GlobalsRecord>> asList(com.lobobrowser.cobra.data.db.Keys.CONSTRAINT_3);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public com.lobobrowser.cobra.data.db.tables.Globals as(final java.lang.String alias) {
    return new com.lobobrowser.cobra.data.db.tables.Globals(alias, this);
  }

  /**
   * Rename this table
   */
  public com.lobobrowser.cobra.data.db.tables.Globals rename(final java.lang.String name) {
    return new com.lobobrowser.cobra.data.db.tables.Globals(name, null);
  }
}
