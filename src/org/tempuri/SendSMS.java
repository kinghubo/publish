
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://tempuri.org/}InfoHeader" minOccurs="0"/>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiverList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pseudoFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "sessionId",
    "sender",
    "smsContent",
    "receiverList",
    "productCode",
    "pseudoFlag"
})
@XmlRootElement(name = "sendSMS")
public class SendSMS {

    protected InfoHeader header;
    protected String sessionId;
    protected String sender;
    protected String smsContent;
    protected String receiverList;
    protected String productCode;
    protected String pseudoFlag;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link InfoHeader }
     *     
     */
    public InfoHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoHeader }
     *     
     */
    public void setHeader(InfoHeader value) {
        this.header = value;
    }

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * Gets the value of the smsContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsContent() {
        return smsContent;
    }

    /**
     * Sets the value of the smsContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsContent(String value) {
        this.smsContent = value;
    }

    /**
     * Gets the value of the receiverList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverList() {
        return receiverList;
    }

    /**
     * Sets the value of the receiverList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverList(String value) {
        this.receiverList = value;
    }

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the pseudoFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPseudoFlag() {
        return pseudoFlag;
    }

    /**
     * Sets the value of the pseudoFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPseudoFlag(String value) {
        this.pseudoFlag = value;
    }

}
