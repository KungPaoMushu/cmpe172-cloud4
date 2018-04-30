/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  var film_actor = sequelize.define('film_actor', {
    actor_id: {
      type: DataTypes.INTEGER(5).UNSIGNED,
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'actor',
        key: 'actor_id'
      },
      onUpdate: 'CASCADE',
      onDelete: 'CASCADE'
    },
    film_id: {
      type: DataTypes.INTEGER(5).UNSIGNED,
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'film',
        key: 'film_id'
      }
    },
    last_update: {
      type: DataTypes.DATE,
      allowNull: false,
      defaultValue: sequelize.fn('current_timestamp')
    }
  }, {
    tableName: 'film_actor',
    timestamps: false
  });

  film_actor.associate = function(models) {
    film_actor.belongsTo(models.actor,{
     foreignKey: 'actor_id',
     onDelete: 'CASCADE',
     onUpdate: 'CASCADE'
   });
  };

  return film_actor;
};
