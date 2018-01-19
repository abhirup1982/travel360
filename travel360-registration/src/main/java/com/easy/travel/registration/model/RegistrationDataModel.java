package com.easy.travel.registration.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userdetails")
public class RegistrationDataModel extends BaseDomainModel implements Serializable {

	@Id
	@Size(max = 64)
	@Column(name = "userName", nullable = false)
	private String userName;

	@Size(max = 64)
	@Column(name = "fullName", nullable = false)
	private String fullName;

	@Size(max = 500)
	@Column(name = "interests", nullable = true)
	private String userInterests;

	@Size(max = 10)
	@Column(name = "gender", nullable = true)
	private String userGender;

	@Size(max = 50)
	@Column(name = "userdob", nullable = true)
	private String userdob;

	@Size(max = 15)
	@Column(name = "password", nullable = true)
	private String userPassword;

	@Size(max = 3)
	@Column(name = "maritalstatus", nullable = true)
	private String userMarried;

	@Size(max = 50)
	@Column(name = "email", nullable = false)
	private String userEmail;

	@Size(max = 50)
	@Column(name = "phone", nullable = false)
	private String userPhone;

	@Size(max = 3)
	@Column(name = "subscribed", nullable = true)
	private String notified;

	@Transient
	private String jwt_token;

	@Transient
	private String lang;

	@Size(max = 50)
	@Column(name = "date_of_marriage", nullable = true)
	private String userMarriedDt;

    @Size(max = 40)
    @Column(name = "occupation", nullable = true)
    private String userOccupation;

    @Size(max = 20)
    @Column(name = "salary", nullable = true)
    private String userSalary;

    @Size(max = 50)
    @Column(name = "kids", nullable = true)
    private String userKids;

    @Size(max = 10)
    @Column(name = "pets", nullable = true)
    private String userPets;

    @Transient
    private List<Notification> notifications = new ArrayList<>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

    public String getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(String userInterests) {
        this.userInterests = userInterests;
    }

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserMarried() {
		return userMarried;
	}

	public void setUserMarried(String userMarried) {
		this.userMarried = userMarried;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getJwt_token() {
		return jwt_token;
	}

	public void setJwt_token(String jwt_token) {
		this.jwt_token = jwt_token;
	}

	public String getNotified() {
		return notified;
	}

	public void setNotified(String notified) {
		this.notified = notified;
	}

	public String getUserdob() {
		return userdob;
	}

	public void setUserdob(String userdob) {
		this.userdob = userdob;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserMarriedDt() {
		return userMarriedDt;
	}

	public void setUserMarriedDt(String userMarriedDt) {
		this.userMarriedDt = userMarriedDt;
	}

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public String getUserSalary() {
        return userSalary;
    }

    public void setUserSalary(String userSalary) {
        this.userSalary = userSalary;
    }

    public String getUserKids() {
        return userKids;
    }

    public void setUserKids(String userKids) {
        this.userKids = userKids;
    }

    public String getUserPets() {
        return userPets;
    }

    public void setUserPets(String userPets) {
        this.userPets = userPets;
    }

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public RegistrationDataModel() {

	}

	public void addNotification(Notification notification) {
	    this.notifications.add(notification);
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
	public String toString() {
		return this.userName + "-" + this.userPassword + "-" + this.userEmail + "-" + this.userPhone + "-" + this.notified;
	}
}
