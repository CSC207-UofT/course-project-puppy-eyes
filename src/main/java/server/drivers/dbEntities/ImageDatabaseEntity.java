package server.drivers.dbEntities;

import javax.persistence.*;

/**
 * A driver class that represents an image on the database
 */
@Entity
@Table(name = "images")
public class ImageDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    private String type;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "asset_id")
    private String assetId;

    public ImageDatabaseEntity() {
    }

    public ImageDatabaseEntity(String assetId, int ownerId, String url, String type) {
        this.assetId = assetId;
        this.ownerId = ownerId;
        this.url = url;
        this.type = type;
    }

    public String getAssetId() {
        return this.assetId;
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public String getUrl() {
        return this.url;
    }

    public String getType() {
        return this.type;
    }

    public void setAssetId(String id) {
        this.assetId = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

}
