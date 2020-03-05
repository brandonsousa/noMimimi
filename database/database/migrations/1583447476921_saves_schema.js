'use strict'

/** @type {import('@adonisjs/lucid/src/Schema')} */
const Schema = use('Schema')

class SavesSchema extends Schema {
  up () {
    this.create('saves', (table) => {
      table.increments()
      table.integer('post_id')
        .unsigned()
        .references('id')
        .inTable('posts')
        .onUpdate('CASCADE')
        .onDelete('CASCADE')
      table.string('owner_id', 80).notNullable()
      table.timestamps()
    })
  }

  down () {
    this.drop('saves')
  }
}

module.exports = SavesSchema
