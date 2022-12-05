/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.openrtb.common.api;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class BidRequest extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BidRequest\",\"namespace\":\"org.openrtb.common.api\",\"fields\":[{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"imp\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Impression\",\"fields\":[{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"banner\",\"type\":[{\"type\":\"record\",\"name\":\"Banner\",\"fields\":[{\"name\":\"w\",\"type\":[\"int\",\"null\"]},{\"name\":\"h\",\"type\":[\"int\",\"null\"]},{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"pos\",\"type\":[\"int\",\"null\"]},{\"name\":\"btype\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"battr\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"mimes\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"topframe\",\"type\":[\"int\",\"null\"]},{\"name\":\"expdir\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"api\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"video\",\"type\":[{\"type\":\"record\",\"name\":\"Video\",\"fields\":[{\"name\":\"mimes\",\"type\":{\"type\":\"array\",\"items\":\"string\"}},{\"name\":\"linearity\",\"type\":[\"int\",\"null\"]},{\"name\":\"minduration\",\"type\":[\"int\",\"null\"]},{\"name\":\"maxduration\",\"type\":[\"int\",\"null\"]},{\"name\":\"protocol\",\"type\":[\"int\",\"null\"]},{\"name\":\"w\",\"type\":[\"int\",\"null\"]},{\"name\":\"h\",\"type\":[\"int\",\"null\"]},{\"name\":\"startdelay\",\"type\":[\"int\",\"null\"]},{\"name\":\"sequence\",\"type\":[\"int\",\"null\"]},{\"name\":\"battr\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"maxextended\",\"type\":[\"int\",\"null\"]},{\"name\":\"minbitrate\",\"type\":[\"int\",\"null\"]},{\"name\":\"maxbitrate\",\"type\":[\"int\",\"null\"]},{\"name\":\"boxingallowed\",\"type\":[\"int\",\"null\"]},{\"name\":\"playbackmethod\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"delivery\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"pos\",\"type\":[\"int\",\"null\"]},{\"name\":\"companionad\",\"type\":[{\"type\":\"array\",\"items\":\"Banner\"},\"null\"]},{\"name\":\"api\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"companiontype\",\"type\":[{\"type\":\"array\",\"items\":\"int\"},\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"displaymanager\",\"type\":[\"string\",\"null\"]},{\"name\":\"displaymanagerver\",\"type\":[\"string\",\"null\"]},{\"name\":\"instl\",\"type\":[\"int\",\"null\"]},{\"name\":\"tagid\",\"type\":[\"string\",\"null\"]},{\"name\":\"bidfloor\",\"type\":[\"float\",\"null\"]},{\"name\":\"bidfloorcur\",\"type\":[\"string\",\"null\"]},{\"name\":\"iframebuster\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]}}},{\"name\":\"site\",\"type\":[{\"type\":\"record\",\"name\":\"Site\",\"fields\":[{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"name\",\"type\":[\"string\",\"null\"]},{\"name\":\"domain\",\"type\":[\"string\",\"null\"]},{\"name\":\"cat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"sectioncat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"pagecat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"page\",\"type\":[\"string\",\"null\"]},{\"name\":\"privacypolicy\",\"type\":[\"int\",\"null\"]},{\"name\":\"ref\",\"type\":[\"string\",\"null\"]},{\"name\":\"search\",\"type\":[\"int\",\"null\"]},{\"name\":\"publisher\",\"type\":[{\"type\":\"record\",\"name\":\"Publisher\",\"fields\":[{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"name\",\"type\":[\"string\",\"null\"]},{\"name\":\"cat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"domain\",\"type\":[\"string\",\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"content\",\"type\":[{\"type\":\"record\",\"name\":\"Content\",\"fields\":[{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"episode\",\"type\":[\"int\",\"null\"]},{\"name\":\"title\",\"type\":[\"string\",\"null\"]},{\"name\":\"series\",\"type\":[\"string\",\"null\"]},{\"name\":\"season\",\"type\":[\"string\",\"null\"]},{\"name\":\"url\",\"type\":[\"string\",\"null\"]},{\"name\":\"cat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"videoquality\",\"type\":[\"int\",\"null\"]},{\"name\":\"keywords\",\"type\":[\"string\",\"null\"]},{\"name\":\"contentrating\",\"type\":[\"string\",\"null\"]},{\"name\":\"userrating\",\"type\":[\"string\",\"null\"]},{\"name\":\"context\",\"type\":[\"string\",\"null\"]},{\"name\":\"livestream\",\"type\":[\"int\",\"null\"]},{\"name\":\"sourcerelationship\",\"type\":[\"int\",\"null\"]},{\"name\":\"producer\",\"type\":[{\"type\":\"record\",\"name\":\"Producer\",\"fields\":[{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"name\",\"type\":[\"string\",\"null\"]},{\"name\":\"cat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"domain\",\"type\":[\"string\",\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"len\",\"type\":[\"int\",\"null\"]},{\"name\":\"qagmediarating\",\"type\":[\"int\",\"null\"]},{\"name\":\"embeddable\",\"type\":[\"int\",\"null\"]},{\"name\":\"language\",\"type\":[\"string\",\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"keywords\",\"type\":[\"string\",\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"app\",\"type\":[{\"type\":\"record\",\"name\":\"App\",\"fields\":[{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"name\",\"type\":[\"string\",\"null\"]},{\"name\":\"domain\",\"type\":[\"string\",\"null\"]},{\"name\":\"cat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"sectioncat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"pagecat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"ver\",\"type\":[\"string\",\"null\"]},{\"name\":\"bundle\",\"type\":[\"string\",\"null\"]},{\"name\":\"privacypolicy\",\"type\":[\"int\",\"null\"]},{\"name\":\"paid\",\"type\":[\"int\",\"null\"]},{\"name\":\"publisher\",\"type\":[\"Publisher\",\"null\"]},{\"name\":\"content\",\"type\":[\"Content\",\"null\"]},{\"name\":\"keywords\",\"type\":[\"string\",\"null\"]},{\"name\":\"storeurl\",\"type\":[\"string\",\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"device\",\"type\":[{\"type\":\"record\",\"name\":\"Device\",\"fields\":[{\"name\":\"dnt\",\"type\":[\"int\",\"null\"]},{\"name\":\"ua\",\"type\":[\"string\",\"null\"]},{\"name\":\"ip\",\"type\":[\"string\",\"null\"]},{\"name\":\"geo\",\"type\":[{\"type\":\"record\",\"name\":\"Geo\",\"fields\":[{\"name\":\"lat\",\"type\":[\"float\",\"null\"]},{\"name\":\"lon\",\"type\":[\"float\",\"null\"]},{\"name\":\"country\",\"type\":[\"string\",\"null\"]},{\"name\":\"region\",\"type\":[\"string\",\"null\"]},{\"name\":\"regionfips104\",\"type\":[\"string\",\"null\"]},{\"name\":\"metro\",\"type\":[\"string\",\"null\"]},{\"name\":\"city\",\"type\":[\"string\",\"null\"]},{\"name\":\"zip\",\"type\":[\"string\",\"null\"]},{\"name\":\"type\",\"type\":[\"int\",\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"didsha1\",\"type\":[\"string\",\"null\"]},{\"name\":\"didmd5\",\"type\":[\"string\",\"null\"]},{\"name\":\"dpidsha1\",\"type\":[\"string\",\"null\"]},{\"name\":\"dpidmd5\",\"type\":[\"string\",\"null\"]},{\"name\":\"ipv6\",\"type\":[\"string\",\"null\"]},{\"name\":\"carrier\",\"type\":[\"string\",\"null\"]},{\"name\":\"language\",\"type\":[\"string\",\"null\"]},{\"name\":\"make\",\"type\":[\"string\",\"null\"]},{\"name\":\"model\",\"type\":[\"string\",\"null\"]},{\"name\":\"os\",\"type\":[\"string\",\"null\"]},{\"name\":\"Osv\",\"type\":[\"string\",\"null\"]},{\"name\":\"Js\",\"type\":[\"int\",\"null\"]},{\"name\":\"connectiontype\",\"type\":[\"int\",\"null\"]},{\"name\":\"deviceType\",\"type\":[\"int\",\"null\"]},{\"name\":\"flashver\",\"type\":[\"string\",\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"user\",\"type\":[{\"type\":\"record\",\"name\":\"User\",\"fields\":[{\"name\":\"Id\",\"type\":[\"string\",\"null\"]},{\"name\":\"buyeruid\",\"type\":[\"string\",\"null\"]},{\"name\":\"yob\",\"type\":[\"int\",\"null\"]},{\"name\":\"gender\",\"type\":[\"string\",\"null\"]},{\"name\":\"keywords\",\"type\":[\"string\",\"null\"]},{\"name\":\"customdata\",\"type\":[\"string\",\"null\"]},{\"name\":\"geo\",\"type\":[\"Geo\",\"null\"]},{\"name\":\"data\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Data\",\"fields\":[{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"name\",\"type\":[\"string\",\"null\"]},{\"name\":\"segment\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Segment\",\"fields\":[{\"name\":\"id\",\"type\":[\"string\",\"null\"]},{\"name\":\"name\",\"type\":[\"string\",\"null\"]},{\"name\":\"value\",\"type\":[\"string\",\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]}},\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]}},\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]},\"null\"]},{\"name\":\"at\",\"type\":[\"int\",\"null\"]},{\"name\":\"tmax\",\"type\":[\"int\",\"null\"]},{\"name\":\"wseat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"allimps\",\"type\":[\"int\",\"null\"]},{\"name\":\"cur\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"bcat\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"badv\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"ext\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence id;
  @Deprecated public java.util.List<org.openrtb.common.api.Impression> imp;
  @Deprecated public org.openrtb.common.api.Site site;
  @Deprecated public org.openrtb.common.api.App app;
  @Deprecated public org.openrtb.common.api.Device device;
  @Deprecated public org.openrtb.common.api.User user;
  @Deprecated public java.lang.Integer at;
  @Deprecated public java.lang.Integer tmax;
  @Deprecated public java.util.List<java.lang.CharSequence> wseat;
  @Deprecated public java.lang.Integer allimps;
  @Deprecated public java.util.List<java.lang.CharSequence> cur;
  @Deprecated public java.util.List<java.lang.CharSequence> bcat;
  @Deprecated public java.util.List<java.lang.CharSequence> badv;
  @Deprecated public java.lang.CharSequence ext;

  /**
   * Default constructor.
   */
  public BidRequest() {}

  /**
   * All-args constructor.
   */
  public BidRequest(java.lang.CharSequence id, java.util.List<org.openrtb.common.api.Impression> imp, org.openrtb.common.api.Site site, org.openrtb.common.api.App app, org.openrtb.common.api.Device device, org.openrtb.common.api.User user, java.lang.Integer at, java.lang.Integer tmax, java.util.List<java.lang.CharSequence> wseat, java.lang.Integer allimps, java.util.List<java.lang.CharSequence> cur, java.util.List<java.lang.CharSequence> bcat, java.util.List<java.lang.CharSequence> badv, java.lang.CharSequence ext) {
    this.id = id;
    this.imp = imp;
    this.site = site;
    this.app = app;
    this.device = device;
    this.user = user;
    this.at = at;
    this.tmax = tmax;
    this.wseat = wseat;
    this.allimps = allimps;
    this.cur = cur;
    this.bcat = bcat;
    this.badv = badv;
    this.ext = ext;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return imp;
    case 2: return site;
    case 3: return app;
    case 4: return device;
    case 5: return user;
    case 6: return at;
    case 7: return tmax;
    case 8: return wseat;
    case 9: return allimps;
    case 10: return cur;
    case 11: return bcat;
    case 12: return badv;
    case 13: return ext;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: imp = (java.util.List<org.openrtb.common.api.Impression>)value$; break;
    case 2: site = (org.openrtb.common.api.Site)value$; break;
    case 3: app = (org.openrtb.common.api.App)value$; break;
    case 4: device = (org.openrtb.common.api.Device)value$; break;
    case 5: user = (org.openrtb.common.api.User)value$; break;
    case 6: at = (java.lang.Integer)value$; break;
    case 7: tmax = (java.lang.Integer)value$; break;
    case 8: wseat = (java.util.List<java.lang.CharSequence>)value$; break;
    case 9: allimps = (java.lang.Integer)value$; break;
    case 10: cur = (java.util.List<java.lang.CharSequence>)value$; break;
    case 11: bcat = (java.util.List<java.lang.CharSequence>)value$; break;
    case 12: badv = (java.util.List<java.lang.CharSequence>)value$; break;
    case 13: ext = (java.lang.CharSequence)value$; break;
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
   * Gets the value of the 'imp' field.
   */
  public java.util.List<org.openrtb.common.api.Impression> getImp() {
    return imp;
  }

  /**
   * Sets the value of the 'imp' field.
   * @param value the value to set.
   */
  public void setImp(java.util.List<org.openrtb.common.api.Impression> value) {
    this.imp = value;
  }

  /**
   * Gets the value of the 'site' field.
   */
  public org.openrtb.common.api.Site getSite() {
    return site;
  }

  /**
   * Sets the value of the 'site' field.
   * @param value the value to set.
   */
  public void setSite(org.openrtb.common.api.Site value) {
    this.site = value;
  }

  /**
   * Gets the value of the 'app' field.
   */
  public org.openrtb.common.api.App getApp() {
    return app;
  }

  /**
   * Sets the value of the 'app' field.
   * @param value the value to set.
   */
  public void setApp(org.openrtb.common.api.App value) {
    this.app = value;
  }

  /**
   * Gets the value of the 'device' field.
   */
  public org.openrtb.common.api.Device getDevice() {
    return device;
  }

  /**
   * Sets the value of the 'device' field.
   * @param value the value to set.
   */
  public void setDevice(org.openrtb.common.api.Device value) {
    this.device = value;
  }

  /**
   * Gets the value of the 'user' field.
   */
  public org.openrtb.common.api.User getUser() {
    return user;
  }

  /**
   * Sets the value of the 'user' field.
   * @param value the value to set.
   */
  public void setUser(org.openrtb.common.api.User value) {
    this.user = value;
  }

  /**
   * Gets the value of the 'at' field.
   */
  public java.lang.Integer getAt() {
    return at;
  }

  /**
   * Sets the value of the 'at' field.
   * @param value the value to set.
   */
  public void setAt(java.lang.Integer value) {
    this.at = value;
  }

  /**
   * Gets the value of the 'tmax' field.
   */
  public java.lang.Integer getTmax() {
    return tmax;
  }

  /**
   * Sets the value of the 'tmax' field.
   * @param value the value to set.
   */
  public void setTmax(java.lang.Integer value) {
    this.tmax = value;
  }

  /**
   * Gets the value of the 'wseat' field.
   */
  public java.util.List<java.lang.CharSequence> getWseat() {
    return wseat;
  }

  /**
   * Sets the value of the 'wseat' field.
   * @param value the value to set.
   */
  public void setWseat(java.util.List<java.lang.CharSequence> value) {
    this.wseat = value;
  }

  /**
   * Gets the value of the 'allimps' field.
   */
  public java.lang.Integer getAllimps() {
    return allimps;
  }

  /**
   * Sets the value of the 'allimps' field.
   * @param value the value to set.
   */
  public void setAllimps(java.lang.Integer value) {
    this.allimps = value;
  }

  /**
   * Gets the value of the 'cur' field.
   */
  public java.util.List<java.lang.CharSequence> getCur() {
    return cur;
  }

  /**
   * Sets the value of the 'cur' field.
   * @param value the value to set.
   */
  public void setCur(java.util.List<java.lang.CharSequence> value) {
    this.cur = value;
  }

  /**
   * Gets the value of the 'bcat' field.
   */
  public java.util.List<java.lang.CharSequence> getBcat() {
    return bcat;
  }

  /**
   * Sets the value of the 'bcat' field.
   * @param value the value to set.
   */
  public void setBcat(java.util.List<java.lang.CharSequence> value) {
    this.bcat = value;
  }

  /**
   * Gets the value of the 'badv' field.
   */
  public java.util.List<java.lang.CharSequence> getBadv() {
    return badv;
  }

  /**
   * Sets the value of the 'badv' field.
   * @param value the value to set.
   */
  public void setBadv(java.util.List<java.lang.CharSequence> value) {
    this.badv = value;
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

  /** Creates a new BidRequest RecordBuilder */
  public static org.openrtb.common.api.BidRequest.Builder newBuilder() {
    return new org.openrtb.common.api.BidRequest.Builder();
  }
  
  /** Creates a new BidRequest RecordBuilder by copying an existing Builder */
  public static org.openrtb.common.api.BidRequest.Builder newBuilder(org.openrtb.common.api.BidRequest.Builder other) {
    return new org.openrtb.common.api.BidRequest.Builder(other);
  }
  
  /** Creates a new BidRequest RecordBuilder by copying an existing BidRequest instance */
  public static org.openrtb.common.api.BidRequest.Builder newBuilder(org.openrtb.common.api.BidRequest other) {
    return new org.openrtb.common.api.BidRequest.Builder(other);
  }
  
  /**
   * RecordBuilder for BidRequest instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<BidRequest>
    implements org.apache.avro.data.RecordBuilder<BidRequest> {

    private java.lang.CharSequence id;
    private java.util.List<org.openrtb.common.api.Impression> imp;
    private org.openrtb.common.api.Site site;
    private org.openrtb.common.api.App app;
    private org.openrtb.common.api.Device device;
    private org.openrtb.common.api.User user;
    private java.lang.Integer at;
    private java.lang.Integer tmax;
    private java.util.List<java.lang.CharSequence> wseat;
    private java.lang.Integer allimps;
    private java.util.List<java.lang.CharSequence> cur;
    private java.util.List<java.lang.CharSequence> bcat;
    private java.util.List<java.lang.CharSequence> badv;
    private java.lang.CharSequence ext;

    /** Creates a new Builder */
    private Builder() {
      super(org.openrtb.common.api.BidRequest.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.openrtb.common.api.BidRequest.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing BidRequest instance */
    private Builder(org.openrtb.common.api.BidRequest other) {
            super(org.openrtb.common.api.BidRequest.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.imp)) {
        this.imp = data().deepCopy(fields()[1].schema(), other.imp);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.site)) {
        this.site = data().deepCopy(fields()[2].schema(), other.site);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.app)) {
        this.app = data().deepCopy(fields()[3].schema(), other.app);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.device)) {
        this.device = data().deepCopy(fields()[4].schema(), other.device);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.user)) {
        this.user = data().deepCopy(fields()[5].schema(), other.user);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.at)) {
        this.at = data().deepCopy(fields()[6].schema(), other.at);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.tmax)) {
        this.tmax = data().deepCopy(fields()[7].schema(), other.tmax);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.wseat)) {
        this.wseat = data().deepCopy(fields()[8].schema(), other.wseat);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.allimps)) {
        this.allimps = data().deepCopy(fields()[9].schema(), other.allimps);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.cur)) {
        this.cur = data().deepCopy(fields()[10].schema(), other.cur);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.bcat)) {
        this.bcat = data().deepCopy(fields()[11].schema(), other.bcat);
        fieldSetFlags()[11] = true;
      }
      if (isValidValue(fields()[12], other.badv)) {
        this.badv = data().deepCopy(fields()[12].schema(), other.badv);
        fieldSetFlags()[12] = true;
      }
      if (isValidValue(fields()[13], other.ext)) {
        this.ext = data().deepCopy(fields()[13].schema(), other.ext);
        fieldSetFlags()[13] = true;
      }
    }

    /** Gets the value of the 'id' field */
    public java.lang.CharSequence getId() {
      return id;
    }
    
    /** Sets the value of the 'id' field */
    public org.openrtb.common.api.BidRequest.Builder setId(java.lang.CharSequence value) {
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
    public org.openrtb.common.api.BidRequest.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'imp' field */
    public java.util.List<org.openrtb.common.api.Impression> getImp() {
      return imp;
    }
    
    /** Sets the value of the 'imp' field */
    public org.openrtb.common.api.BidRequest.Builder setImp(java.util.List<org.openrtb.common.api.Impression> value) {
      validate(fields()[1], value);
      this.imp = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'imp' field has been set */
    public boolean hasImp() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'imp' field */
    public org.openrtb.common.api.BidRequest.Builder clearImp() {
      imp = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'site' field */
    public org.openrtb.common.api.Site getSite() {
      return site;
    }
    
    /** Sets the value of the 'site' field */
    public org.openrtb.common.api.BidRequest.Builder setSite(org.openrtb.common.api.Site value) {
      validate(fields()[2], value);
      this.site = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'site' field has been set */
    public boolean hasSite() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'site' field */
    public org.openrtb.common.api.BidRequest.Builder clearSite() {
      site = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'app' field */
    public org.openrtb.common.api.App getApp() {
      return app;
    }
    
    /** Sets the value of the 'app' field */
    public org.openrtb.common.api.BidRequest.Builder setApp(org.openrtb.common.api.App value) {
      validate(fields()[3], value);
      this.app = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'app' field has been set */
    public boolean hasApp() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'app' field */
    public org.openrtb.common.api.BidRequest.Builder clearApp() {
      app = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'device' field */
    public org.openrtb.common.api.Device getDevice() {
      return device;
    }
    
    /** Sets the value of the 'device' field */
    public org.openrtb.common.api.BidRequest.Builder setDevice(org.openrtb.common.api.Device value) {
      validate(fields()[4], value);
      this.device = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'device' field has been set */
    public boolean hasDevice() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'device' field */
    public org.openrtb.common.api.BidRequest.Builder clearDevice() {
      device = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'user' field */
    public org.openrtb.common.api.User getUser() {
      return user;
    }
    
    /** Sets the value of the 'user' field */
    public org.openrtb.common.api.BidRequest.Builder setUser(org.openrtb.common.api.User value) {
      validate(fields()[5], value);
      this.user = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'user' field has been set */
    public boolean hasUser() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'user' field */
    public org.openrtb.common.api.BidRequest.Builder clearUser() {
      user = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'at' field */
    public java.lang.Integer getAt() {
      return at;
    }
    
    /** Sets the value of the 'at' field */
    public org.openrtb.common.api.BidRequest.Builder setAt(java.lang.Integer value) {
      validate(fields()[6], value);
      this.at = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'at' field has been set */
    public boolean hasAt() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'at' field */
    public org.openrtb.common.api.BidRequest.Builder clearAt() {
      at = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'tmax' field */
    public java.lang.Integer getTmax() {
      return tmax;
    }
    
    /** Sets the value of the 'tmax' field */
    public org.openrtb.common.api.BidRequest.Builder setTmax(java.lang.Integer value) {
      validate(fields()[7], value);
      this.tmax = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'tmax' field has been set */
    public boolean hasTmax() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'tmax' field */
    public org.openrtb.common.api.BidRequest.Builder clearTmax() {
      tmax = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'wseat' field */
    public java.util.List<java.lang.CharSequence> getWseat() {
      return wseat;
    }
    
    /** Sets the value of the 'wseat' field */
    public org.openrtb.common.api.BidRequest.Builder setWseat(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[8], value);
      this.wseat = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'wseat' field has been set */
    public boolean hasWseat() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'wseat' field */
    public org.openrtb.common.api.BidRequest.Builder clearWseat() {
      wseat = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /** Gets the value of the 'allimps' field */
    public java.lang.Integer getAllimps() {
      return allimps;
    }
    
    /** Sets the value of the 'allimps' field */
    public org.openrtb.common.api.BidRequest.Builder setAllimps(java.lang.Integer value) {
      validate(fields()[9], value);
      this.allimps = value;
      fieldSetFlags()[9] = true;
      return this; 
    }
    
    /** Checks whether the 'allimps' field has been set */
    public boolean hasAllimps() {
      return fieldSetFlags()[9];
    }
    
    /** Clears the value of the 'allimps' field */
    public org.openrtb.common.api.BidRequest.Builder clearAllimps() {
      allimps = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /** Gets the value of the 'cur' field */
    public java.util.List<java.lang.CharSequence> getCur() {
      return cur;
    }
    
    /** Sets the value of the 'cur' field */
    public org.openrtb.common.api.BidRequest.Builder setCur(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[10], value);
      this.cur = value;
      fieldSetFlags()[10] = true;
      return this; 
    }
    
    /** Checks whether the 'cur' field has been set */
    public boolean hasCur() {
      return fieldSetFlags()[10];
    }
    
    /** Clears the value of the 'cur' field */
    public org.openrtb.common.api.BidRequest.Builder clearCur() {
      cur = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    /** Gets the value of the 'bcat' field */
    public java.util.List<java.lang.CharSequence> getBcat() {
      return bcat;
    }
    
    /** Sets the value of the 'bcat' field */
    public org.openrtb.common.api.BidRequest.Builder setBcat(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[11], value);
      this.bcat = value;
      fieldSetFlags()[11] = true;
      return this; 
    }
    
    /** Checks whether the 'bcat' field has been set */
    public boolean hasBcat() {
      return fieldSetFlags()[11];
    }
    
    /** Clears the value of the 'bcat' field */
    public org.openrtb.common.api.BidRequest.Builder clearBcat() {
      bcat = null;
      fieldSetFlags()[11] = false;
      return this;
    }

    /** Gets the value of the 'badv' field */
    public java.util.List<java.lang.CharSequence> getBadv() {
      return badv;
    }
    
    /** Sets the value of the 'badv' field */
    public org.openrtb.common.api.BidRequest.Builder setBadv(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[12], value);
      this.badv = value;
      fieldSetFlags()[12] = true;
      return this; 
    }
    
    /** Checks whether the 'badv' field has been set */
    public boolean hasBadv() {
      return fieldSetFlags()[12];
    }
    
    /** Clears the value of the 'badv' field */
    public org.openrtb.common.api.BidRequest.Builder clearBadv() {
      badv = null;
      fieldSetFlags()[12] = false;
      return this;
    }

    /** Gets the value of the 'ext' field */
    public java.lang.CharSequence getExt() {
      return ext;
    }
    
    /** Sets the value of the 'ext' field */
    public org.openrtb.common.api.BidRequest.Builder setExt(java.lang.CharSequence value) {
      validate(fields()[13], value);
      this.ext = value;
      fieldSetFlags()[13] = true;
      return this; 
    }
    
    /** Checks whether the 'ext' field has been set */
    public boolean hasExt() {
      return fieldSetFlags()[13];
    }
    
    /** Clears the value of the 'ext' field */
    public org.openrtb.common.api.BidRequest.Builder clearExt() {
      ext = null;
      fieldSetFlags()[13] = false;
      return this;
    }

    @Override
    public BidRequest build() {
      try {
        BidRequest record = new BidRequest();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.imp = fieldSetFlags()[1] ? this.imp : (java.util.List<org.openrtb.common.api.Impression>) defaultValue(fields()[1]);
        record.site = fieldSetFlags()[2] ? this.site : (org.openrtb.common.api.Site) defaultValue(fields()[2]);
        record.app = fieldSetFlags()[3] ? this.app : (org.openrtb.common.api.App) defaultValue(fields()[3]);
        record.device = fieldSetFlags()[4] ? this.device : (org.openrtb.common.api.Device) defaultValue(fields()[4]);
        record.user = fieldSetFlags()[5] ? this.user : (org.openrtb.common.api.User) defaultValue(fields()[5]);
        record.at = fieldSetFlags()[6] ? this.at : (java.lang.Integer) defaultValue(fields()[6]);
        record.tmax = fieldSetFlags()[7] ? this.tmax : (java.lang.Integer) defaultValue(fields()[7]);
        record.wseat = fieldSetFlags()[8] ? this.wseat : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[8]);
        record.allimps = fieldSetFlags()[9] ? this.allimps : (java.lang.Integer) defaultValue(fields()[9]);
        record.cur = fieldSetFlags()[10] ? this.cur : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[10]);
        record.bcat = fieldSetFlags()[11] ? this.bcat : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[11]);
        record.badv = fieldSetFlags()[12] ? this.badv : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[12]);
        record.ext = fieldSetFlags()[13] ? this.ext : (java.lang.CharSequence) defaultValue(fields()[13]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
