package server.drivers.dbEntities;

import javax.persistence.*;

/**
 * A DatabaseEntity class that models a relationship between two other entities
 */
@Entity
@Table(name = "relations")
public class RelationDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "from_id")
    private int fromId;

    @Column(name = "to_id")
    private int toId;

    @Column(name = "relation_type")
    private String relationType;

    public RelationDatabaseEntity() {};

    public RelationDatabaseEntity(int fromId, int toId, String relationType) {
        this.fromId = fromId;
        this.toId = toId;
        this.relationType = relationType;
    }

    public int getId() {
        return this.id;
    }

    public int getFromId() {
        return this.fromId;
    }

    public int getToId() {
        return this.toId;
    }

    public String getRelationType() {
        return this.relationType;
    }

}
