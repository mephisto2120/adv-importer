package com.tryton.adv.importer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "datasource")
public class DatasourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ds_entity_seq", sequenceName = "ds_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ds_entity_seq")
    @Basic(optional = false)
    @Column(name = "ds_id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "ds_name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cpgDsId")
    private List<CampaignEntity> campaignEntityList;

    public DatasourceEntity() {
    }

    public DatasourceEntity(Long id) {
        this.id = id;
    }

    public DatasourceEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long dsId) {
        this.id = dsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String dsName) {
        this.name = dsName;
    }

    public List<CampaignEntity> getCampaignEntityList() {
        return campaignEntityList;
    }

    public void setCampaignEntityList(List<CampaignEntity> campaignEntityList) {
        this.campaignEntityList = campaignEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatasourceEntity)) {
            return false;
        }
        DatasourceEntity other = (DatasourceEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tryton.adv.importer.entity.DatasourceEntity[ dsId=" + id + " ]";
    }

}
