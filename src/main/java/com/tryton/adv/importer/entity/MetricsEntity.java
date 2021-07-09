package com.tryton.adv.importer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "metrics")
@NamedQueries({
        @NamedQuery(name = "MetricsEntity.findAll", query = "SELECT m FROM MetricsEntity m"),
        @NamedQuery(name = "MetricsEntity.findByMetId", query = "SELECT m FROM MetricsEntity m WHERE m.id = :metId"),
        @NamedQuery(name = "MetricsEntity.findByMetClicks", query = "SELECT m FROM MetricsEntity m WHERE m.clicks = :metClicks"),
        @NamedQuery(name = "MetricsEntity.findByMetImpressions", query = "SELECT m FROM MetricsEntity m WHERE m.impressions = :metImpressions"),
        @NamedQuery(name = "MetricsEntity.findByMetDaily", query = "SELECT m FROM MetricsEntity m WHERE m.daily = :metDaily")})
public class MetricsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "met_id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "met_clicks")
    private int clicks;
    @Basic(optional = false)
    @Column(name = "met_impressions")
    private int impressions;
    @Basic(optional = false)
    @Column(name = "met_daily")
    @Temporal(TemporalType.TIMESTAMP)
    private Date daily;
    @JoinColumn(name = "campaign_cpg_id", referencedColumnName = "cpg_id")
    @ManyToOne(optional = false)
    private CampaignEntity campaignCpgId;

    public MetricsEntity() {
    }

    public MetricsEntity(Long id) {
        this.id = id;
    }

    public MetricsEntity(Long id, int clicks, int impressions, Date daily) {
        this.id = id;
        this.clicks = clicks;
        this.impressions = impressions;
        this.daily = daily;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long metId) {
        this.id = metId;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int metClicks) {
        this.clicks = metClicks;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int metImpressions) {
        this.impressions = metImpressions;
    }

    public Date getDaily() {
        return daily;
    }

    public void setDaily(Date metDaily) {
        this.daily = metDaily;
    }

    public CampaignEntity getCampaignCpgId() {
        return campaignCpgId;
    }

    public void setCampaignCpgId(CampaignEntity campaignCpgId) {
        this.campaignCpgId = campaignCpgId;
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
        if (!(object instanceof MetricsEntity)) {
            return false;
        }
        MetricsEntity other = (MetricsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tryton.adv.importer.entity.MetricsEntity[ metId=" + id + " ]";
    }

}
