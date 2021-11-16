package server.use_cases;

import server.use_cases.repo_abstracts.IRelationRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DummyRelationRepository implements IRelationRepository {

    public class Relation {

        private int fromId, toId;
        private String relationType;

        public Relation(int fromId, int toId, String relationType) {
            this.fromId = fromId;
            this.toId = toId;
            this.relationType = relationType;
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

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Relation))
                return false;

            Relation other = (Relation) o;

            return other.fromId == this.fromId && other.toId == this.toId && other.getRelationType().equals(this.getRelationType());
        }

        @Override
        public String toString() {
            return "fromId: " + fromId + ", toId: " + toId + ", relationType: " + relationType;
        }
    }

    private Set<Relation> relations;

    public DummyRelationRepository() {
        this.relations = new HashSet<>();
    }

    @Override
    public void addRelation(int fromId, int toId, String relationType) {
        this.relations.add(new Relation(fromId, toId, relationType));
    }

    @Override
    public void removeRelation(int fromId, int toId, String relationType) {
        for (Relation relation : relations) {
            if (relation.getFromId() == fromId && relation.getToId() == toId && relation.getRelationType().equals(relationType)) {
                relations.remove(relation);
            }
        }
    }

    @Override
    public boolean hasRelation(int fromId, int toId, String relationType) {
        for (Relation relation : relations) {
            if (relation.getFromId() == fromId && relation.getToId() == toId && relation.getRelationType().equals(relationType)) {
                return true;
            }
        }

        return false;
    }

    public Set<Relation> getRelations() {
        return this.relations;
    }
}
