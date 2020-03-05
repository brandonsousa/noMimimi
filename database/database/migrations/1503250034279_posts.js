'use strict'

/** @type {import('@adonisjs/lucid/src/Schema')} */
const Schema = use('Schema')

class UserSchema extends Schema {
  up () {
    this.create('posts', (table) => {
      table.increments()
      table.string('owner_id', 80).notNullable()
      table.string('hashtags', 60).notNullable()
      table.string('content', 240).notNullable()
      table.timestamps()
    })
  }

  down () {
    this.drop('posts')
  }
}

module.exports = UserSchema
