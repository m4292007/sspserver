/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.openrtb.common.api;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Impression extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Impression\",\"namespace\":\"org.openrtb.common.api\",\"fields\":[{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"banner\",\"type\":[{\"type\":\"record\",\"name\":\"Banner\",\"fields\":[{\"name\":\"w\",\"type\":[\"int\",\"null\"]},{\"name\":\"h\",\"type\":[\"int\",\"null\"]},{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"pos\",\"type\":[\"int\",\"null\"]},{\"name\":\"btype\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"battr\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"mimes\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"topframe\",\"type\":[\"int\",\"null\"]},{\"name\":\"expdir\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"api\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"video\",\"type\":[{\"type\":\"record\",\"name\":\"Video\",\"fields\":[{\"name\":\"mimes\",\"type\":{\"type\":\"array\",\"items\":\"string\"}},{\"name\":\"linearity\",\"type\":[\"int\",\"null\"]},{\"name\":\"minduration\",\"type\":[\"int\",\"null\"]},{\"name\":\"maxduration\",\"type\":[\"int\",\"null\"]},{\"name\":\"protocol\",\"type\":[\"int\",\"null\"]},{\"name\":\"w\",\"type\":[\"int\",\"null\"]},{\"name\":\"h\",\"type\":[\"int\",\"null\"]},{\"name\":\"startdelay\",\"type\":[\"int\",\"null\"]},{\"name\":\"sequence\",\"type\":[\"int\",\"null\"]},{\"name\":\"battr\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"maxextended\",\"type\":[\"int\",\"null\"]},{\"name\":\"minbitrate\",\"type\":[\"int\",\"null\"]},{\"name\":\"maxbitrate\",\"type\":[\"int\",\"null\"]},{\"name\":\"boxingallowed\",\"type\":[\"int\",\"null\"]},{\"name\":\"playbackmethod\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"delivery\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"pos\",\"type\":[\"int\",\"null\"]},{\"name\":\"companionad\",\"type\":[{\"type\":\"array\",\"items\":\"Banner\"},\"null\"]},{\"name\":\"api\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"companiontype\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"displaymanager\",\"type\":[\"string\",\"null\"]},{\"name\":\"displaymanagerver\",\"type\":[\"string\",\"null\"]},{\"name\":\"instl\",\"type\":[\"int\",\"null\"]},{\"name\":\"tagid\",\"type\":[\"string\",\"null\"]},{\"name\":\"bidfloor\",\"type\":[\"float\",\"null\"]},{\"name\":\"bidfloorcur\",\"type\":[\"string\",\"null\"]},{\"name\":\"iframebuster\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence id;
  @Deprecated public org.openrtb.common.api.Banner banner;
  @Deprecated public org.openrtb.common.api.Video video;
  @Deprecated public java.lang.CharSequence displaymanager;
  @Deprecated public java.lang.CharSequence displaymanagerver;
  @Deprecated public java.lang.Integer instl;
  @Deprecated public java.lang.CharSequence tagid;
  @Deprecated public java.lang.Float bidfloor;
  @Deprecated public java.lang.CharSequence bidfloorcur;
  @Deprecated public java.util.List<java.lang.CharSequence> iframebuster;
  @Deprecated public java.lang.CharSequence ext;

  /**
   * Default constructor.
   */
  public Impression() {}

  /**
   * All-args constructor.
   */
  public Impression(java.lang.CharSequence id, org.openrtb.common.api.Banner banner, org.openrtb.common.api.Video video, java.lang.CharSequence displaymanager, java.lang.CharSequence displaymanagerver, java.lang.Integer instl, java.lang.CharSequence tagid, java.lang.Float bidfloor, java.lang.CharSequence bidfloorcur, java.util.List<java.lang.CharSequence> iframebuster, java.lang.CharSequence ext) {
    this.id = id;
    this.banner = banner;
    this.video = video;
    this.displaymanager = displaymanager;
    this.displaymanagerver = displaymanagerver;
    this.instl = instl;
    this.tagid = tagid;
    this.bidfloor = bidfloor;
    this.bidfloorcur = bidfloorcur;
    this.iframebuster = iframebuster;
    this.ext = ext;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return banner;
    case 2: return video;
    case 3: return displaymanager;
    case 4: return displaymanagerver;
    case 5: return instl;
    case 6: return tagid;
    case 7: return bidfloor;
    case 8: return bidfloorcur;
    case 9: return iframebuster;
    case 10: return ext;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: banner = (org.openrtb.common.api.Banner)value$; break;
    case 2: video = (org.openrtb.common.api.Video)value$; break;
    case 3: displaymanager = (java.lang.CharSequence)value$; break;
    case 4: displaymanagerver = (java.lang.CharSequence)value$; break;
    case 5: instl = (java.lang.Integer)value$; break;
    case 6: tagid = (java.lang.CharSequence)value$; break;
    case 7: bidfloor = (java.lang.Float)value$; break;
    case 8: bidfloorcur = (java.lang.CharSequence)value$; break;
    case 9: iframebuster = (java.util.List<java.lang.CharSequence>)value$; break;
    case 10: ext = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   */
  public java.lang.CharSequence getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.CharSequence value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'banner' field.
   */
  public org.openrtb.common.api.Banner getBanner() {
    return banner;
  }

  /**
   * Sets the value of the 'banner' field.
   * @param value the value to set.
   */
  public void setBanner(org.openrtb.common.api.Banner value) {
    this.banner = value;
  }

  /**
   * Gets the value of the 'video' field.
   */
  public org.openrtb.common.api.Video getVideo() {
    return video;
  }

  /**
   * Sets the value of the 'video' field.
   * @param value the value to set.
   */
  public void setVideo(org.openrtb.common.api.Video value) {
    this.video = value;
  }

  /**
   * Gets the value of the 'displaymanager' field.
   */
  public java.lang.CharSequence getDisplaymanager() {
    return displaymanager;
  }

  /**
   * Sets the value of the 'displaymanager' field.
   * @param value the value to set.
   */
  public void setDisplaymanager(java.lang.CharSequence value) {
    this.displaymanager = value;
  }

  /**
   * Gets the value of the 'displaymanagerver' field.
   */
  public java.lang.CharSequence getDisplaymanagerver() {
    return displaymanagerver;
  }

  /**
   * Sets the value of the 'displaymanagerver' field.
   * @param value the value to set.
   */
  public void setDisplaymanagerver(java.lang.CharSequence value) {
    this.displaymanagerver = value;
  }

  /**
   * Gets the value of the 'instl' field.
   */
  public java.lang.Integer getInstl() {
    return instl;
  }

  /**
   * Sets the value of the 'instl' field.
   * @param value the value to set.
   */
  public void setInstl(java.lang.Integer value) {
    this.instl = value;
  }

  /**
   * Gets the value of the 'tagid' field.
   */
  public java.lang.CharSequence getTagid() {
    return tagid;
  }

  /**
   * Sets the value of the 'tagid' field.
   * @param value the value to set.
   */
  public void setTagid(java.lang.CharSequence value) {
    this.tagid = value;
  }

  /**
   * Gets the value of the 'bidfloor' field.
   */
  public java.lang.Float getBidfloor() {
    return bidfloor;
  }

  /**
   * Sets the value of the 'bidfloor' field.
   * @param value the value to set.
   */
  public void setBidfloor(java.lang.Float value) {
    this.bidfloor = value;
  }

  /**
   * Gets the value of the 'bidfloorcur' field.
   */
  public java.lang.CharSequence getBidfloorcur() {
    return bidfloorcur;
  }

  /**
   * Sets the value of the 'bidfloorcur' field.
   * @param value the value to set.
   */
  public void setBidfloorcur(java.lang.CharSequence value) {
    this.bidfloorcur = value;
  }

  /**
   * Gets the value of the 'iframebuster' field.
   */
  public java.util.List<java.lang.CharSequence> getIframebuster() {
    return iframebuster;
  }

  /**
   * Sets the value of the 'iframebuster' field.
   * @param value the value to set.
   */
  public void setIframebuster(java.util.List<java.lang.CharSequence> value) {
    this.iframebuster = value;
  }

  /**
   * Gets the value of the 'ext' field.
   */
  public java.lang.CharSequence getExt() {
    return ext;
  }

  /**
   * Sets the value of the 'ext' field.
   * @param value the value to set.
   */
  public void setExt(java.lang.CharSequence value) {
    this.ext = value;
  }

  /** Creates a new Impression RecordBuilder */
  public static org.openrtb.common.api.Impression.Builder newBuilder() {
    return new org.openrtb.common.api.Impression.Builder();
  }
  
  /** Creates a new Impression RecordBuilder by copying an existing Builder */
  public static org.openrtb.common.api.Impression.Builder newBuilder(org.openrtb.common.api.Impression.Builder other) {
    return new org.openrtb.common.api.Impression.Builder(other);
  }
  
  /** Creates a new Impression RecordBuilder by copying an existing Impression instance */
  public static org.openrtb.common.api.Impression.Builder newBuilder(org.openrtb.common.api.Impression other) {
    return new org.openrtb.common.api.Impression.Builder(other);
  }
  
  /**
   * RecordBuilder for Impression instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Impression>
    implements org.apache.avro.data.RecordBuilder<Impression> {

    private java.lang.CharSequence id;
    private org.openrtb.common.api.Banner banner;
    private org.openrtb.common.api.Video video;
    private java.lang.CharSequence displaymanager;
    private java.lang.CharSequence displaymanagerver;
    private java.lang.Integer instl;
    private java.lang.CharSequence tagid;
    private java.lang.Float bidfloor;
    private java.lang.CharSequence bidfloorcur;
    private java.util.List<java.lang.CharSequence> iframebuster;
    private java.lang.CharSequence ext;

    /** Creates a new Builder */
    private Builder() {
      super(org.openrtb.common.api.Impression.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.openrtb.common.api.Impression.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing Impression instance */
    private Builder(org.openrtb.common.api.Impression other) {
            super(org.openrtb.common.api.Impression.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.banner)) {
        this.banner = data().deepCopy(fields()[1].schema(), other.banner);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.video)) {
        this.video = data().deepCopy(fields()[2].schema(), other.video);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.displaymanager)) {
        this.displaymanager = data().deepCopy(fields()[3].schema(), other.displaymanager);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.displaymanagerver)) {
        this.displaymanagerver = data().deepCopy(fields()[4].schema(), other.displaymanagerver);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.instl)) {
        this.instl = data().deepCopy(fields()[5].schema(), other.instl);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.tagid)) {
        this.tagid = data().deepCopy(fields()[6].schema(), other.tagid);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.bidfloor)) {
        this.bidfloor = data().deepCopy(fields()[7].schema(), other.bidfloor);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.bidfloorcur)) {
        this.bidfloorcur = data().deepCopy(fields()[8].schema(), other.bidfloorcur);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.iframebuster)) {
        this.iframebuster = data().deepCopy(fields()[9].schema(), other.iframebuster);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.ext)) {
        this.ext = data().deepCopy(fields()[10].schema(), other.ext);
        fieldSetFlags()[10] = true;
      }
    }

    /** Gets the value of the 'id' field */
    public java.lang.CharSequence getId() {
      return id;
    }
    
    /** Sets the value of the 'id' field */
    public org.openrtb.common.api.Impression.Builder setId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'id' field has been set */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'id' field */
    public org.openrtb.common.api.Impression.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'banner' field */
    public org.openrtb.common.api.Banner getBanner() {
      return banner;
    }
    
    /** Sets the value of the 'banner' field */
    public org.openrtb.common.api.Impression.Builder setBanner(org.openrtb.common.api.Banner value) {
      validate(fields()[1], value);
      this.banner = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'banner' field has been set */
    public boolean hasBanner() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'banner' field */
    public org.openrtb.common.api.Impression.Builder clearBanner() {
      banner = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'video' field */
    public org.openrtb.common.api.Video getVideo() {
      return video;
    }
    
    /** Sets the value of the 'video' field */
    public org.openrtb.common.api.Impression.Builder setVideo(org.openrtb.common.api.Video value) {
      validate(fields()[2], value);
      this.video = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'video' field has been set */
    public boolean hasVideo() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'video' field */
    public org.openrtb.common.api.Impression.Builder clearVideo() {
      video = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'displaymanager' field */
    public java.lang.CharSequence getDisplaymanager() {
      return displaymanager;
    }
    
    /** Sets the value of the 'displaymanager' field */
    public org.openrtb.common.api.Impression.Builder setDisplaymanager(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.displaymanager = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'displaymanager' field has been set */
    public boolean hasDisplaymanager() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'displaymanager' field */
    public org.openrtb.common.api.Impression.Builder clearDisplaymanager() {
      displaymanager = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'displaymanagerver' field */
    public java.lang.CharSequence getDisplaymanagerver() {
      return displaymanagerver;
    }
    
    /** Sets the value of the 'displaymanagerver' field */
    public org.openrtb.common.api.Impression.Builder setDisplaymanagerver(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.displaymanagerver = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'displaymanagerver' field has been set */
    public boolean hasDisplaymanagerver() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'displaymanagerver' field */
    public org.openrtb.common.api.Impression.Builder clearDisplaymanagerver() {
      displaymanagerver = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'instl' field */
    public java.lang.Integer getInstl() {
      return instl;
    }
    
    /** Sets the value of the 'instl' field */
    public org.openrtb.common.api.Impression.Builder setInstl(java.lang.Integer value) {
      validate(fields()[5], value);
      this.instl = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'instl' field has been set */
    public boolean hasInstl() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'instl' field */
    public org.openrtb.common.api.Impression.Builder clearInstl() {
      instl = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'tagid' field */
    public java.lang.CharSequence getTagid() {
      return tagid;
    }
    
    /** Sets the value of the 'tagid' field */
    public org.openrtb.common.api.Impression.Builder setTagid(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.tagid = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'tagid' field has been set */
    public boolean hasTagid() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'tagid' field */
    public org.openrtb.common.api.Impression.Builder clearTagid() {
      tagid = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'bidfloor' field */
    public java.lang.Float getBidfloor() {
      return bidfloor;
    }
    
    /** Sets the value of the 'bidfloor' field */
    public org.openrtb.common.api.Impression.Builder setBidfloor(java.lang.Float value) {
      validate(fields()[7], value);
      this.bidfloor = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'bidfloor' field has been set */
    public boolean hasBidfloor() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'bidfloor' field */
    public org.openrtb.common.api.Impression.Builder clearBidfloor() {
      bidfloor = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'bidfloorcur' field */
    public java.lang.CharSequence getBidfloorcur() {
      return bidfloorcur;
    }
    
    /** Sets the value of the 'bidfloorcur' field */
    public org.openrtb.common.api.Impression.Builder setBidfloorcur(java.lang.CharSequence value) {
      validate(fields()[8], value);
      this.bidfloorcur = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'bidfloorcur' field has been set */
    public boolean hasBidfloorcur() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'bidfloorcur' field */
    public org.openrtb.common.api.Impression.Builder clearBidfloorcur() {
      bidfloorcur = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /** Gets the value of the 'iframebuster' field */
    public java.util.List<java.lang.CharSequence> getIframebuster() {
      return iframebuster;
    }
    
    /** Sets the value of the 'iframebuster' field */
    public org.openrtb.common.api.Impression.Builder setIframebuster(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[9], value);
      this.iframebuster = value;
      fieldSetFlags()[9] = true;
      return this; 
    }
    
    /** Checks whether the 'iframebuster' field has been set */
    public boolean hasIframebuster() {
      return fieldSetFlags()[9];
    }
    
    /** Clears the value of the 'iframebuster' field */
    public org.openrtb.common.api.Impression.Builder clearIframebuster() {
      iframebuster = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /** Gets the value of the 'ext' field */
    public java.lang.CharSequence getExt() {
      return ext;
    }
    
    /** Sets the value of the 'ext' field */
    public org.openrtb.common.api.Impression.Builder setExt(java.lang.CharSequence value) {
      validate(fields()[10], value);
      this.ext = value;
      fieldSetFlags()[10] = true;
      return this; 
    }
    
    /** Checks whether the 'ext' field has been set */
    public boolean hasExt() {
      return fieldSetFlags()[10];
    }
    
    /** Clears the value of the 'ext' field */
    public org.openrtb.common.api.Impression.Builder clearExt() {
      ext = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    @Override
    public Impression build() {
      try {
        Impression record = new Impression();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.banner = fieldSetFlags()[1] ? this.banner : (org.openrtb.common.api.Banner) defaultValue(fields()[1]);
        record.video = fieldSetFlags()[2] ? this.video : (org.openrtb.common.api.Video) defaultValue(fields()[2]);
        record.displaymanager = fieldSetFlags()[3] ? this.displaymanager : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.displaymanagerver = fieldSetFlags()[4] ? this.displaymanagerver : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.instl = fieldSetFlags()[5] ? this.instl : (java.lang.Integer) defaultValue(fields()[5]);
        record.tagid = fieldSetFlags()[6] ? this.tagid : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.bidfloor = fieldSetFlags()[7] ? this.bidfloor : (java.lang.Float) defaultValue(fields()[7]);
        record.bidfloorcur = fieldSetFlags()[8] ? this.bidfloorcur : (java.lang.CharSequence) defaultValue(fields()[8]);
        record.iframebuster = fieldSetFlags()[9] ? this.iframebuster : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[9]);
        record.ext = fieldSetFlags()[10] ? this.ext : (java.lang.CharSequence) defaultValue(fields()[10]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
