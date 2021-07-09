package com.tryton.adv.importer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "campaign")
public class CampaignEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "cpg_entity_seq", sequenceName = "cpg_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_entity_seq")
    @Basic(optional = false)
    @Column(name = "cpg_id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "cpg_name")
    private String name;
    @JoinColumn(name = "cpg_ds_id", referencedColumnName = "ds_id")
    @ManyToOne(optional = false)
    private DatasourceEntity cpgDsId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campaignCpgId")
    private List<MetricsEntity> metricsEntityList;

    public CampaignEntity() {
    }

    public CampaignEntity(Long id) {
        this.id = id;
    }

    public CampaignEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long cpgId) {
        this.id = cpgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String cpgName) {
        this.name = cpgName;
    }

    public DatasourceEntity getCpgDsId() {
        return cpgDsId;
    }

    public void setCpgDsId(DatasourceEntity cpgDsId) {
        this.cpgDsId = cpgDsId;
    }

    public List<MetricsEntity> getMetricsEntityList() {
        return metricsEntityList;
    }

    public void setMetricsEntityList(List<MetricsEntity> metricsEntityList) {
        this.metricsEntityList = metricsEntityList;
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
        if (!(object instanceof CampaignEntity)) {
            return false;
        }
        CampaignEntity other = (CampaignEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tryton.adv.importer.entity.CampaignEntity[ cpgId=" + id + " ]";
    }

}
