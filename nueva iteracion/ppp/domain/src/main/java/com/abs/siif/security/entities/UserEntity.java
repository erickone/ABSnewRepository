package com.abs.siif.security.entities;

import com.abs.siif.budget.entities.BudgetKeyAdditionalEntity;
import com.abs.siif.common.entities.ColectiveEntity;
import com.abs.siif.programming.entities.ObservationEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 * 
 * Esta entidad define los atributos para un usuario dentro del SIIF, clave, descripción 
 * y fecha de último acceso
 * 
 */

@Entity
@Table(name = "siifabsusuario")
public class UserEntity  implements 
        Serializable, Comparable<UserEntity>{
   

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdUsuario", nullable = false)
    private Long userId;

    //Contraseña del usuario
    @Column(name = "Contrasenia", length = 25)
    private String userPassword;

    //Usuario que se usara en el Login
    @Column(name = "Usuario", length = 25)
    private String userName;

    // Descripción del Usuario
    @Column(name="Descripcion",nullable=false,length=150)
    private String userDescription;

    // Bandera que indica si el usuario puede cambiar la contraseña
    @Column(name = "CambiarContrasenia")
    private boolean userCanChangePassword;

    // Bandera que indica si la contraseña del usuario expira
    @Column(name = "ExpiraContrasenia")
    private boolean userExpiresPassword;

    // Bandera que indica si el usuario esta habilitado
    @Column(name = "Habilitado")
    private boolean userIsEnabled;

    // Fecha de último acceso del sistema al usuario
    @Column(name = "FechaUltimoAcceso")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date userLastAccessDate;

       // Bandera que indica si el usuario puede cambiar la contraseña
    @OneToMany(mappedBy = "observationUser", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<ObservationEntity> observationUser;

    // Generación de estructura para relacionar usuarios con perfiles
    @ManyToMany
    @JoinTable(name = "siifsegusuperfil", joinColumns = {
        @JoinColumn(name = "IdUsuario", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdPerfil",
        nullable = false, updatable = false)})
    private List<ProfileEntity> userProfile;
    
    @OneToOne
    //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "IdColectiva", nullable = true)
    private ColectiveEntity userColective;
    
    @OneToMany(mappedBy = "viewUserModuleUser",fetch= FetchType.LAZY)
    private List<ViewUserModuleEntity> userViewsByModule;
    
     @OneToMany(mappedBy = "budgetKeyAdditionalUser",fetch= FetchType.LAZY)
    private List<BudgetKeyAdditionalEntity> budgetKeyAdditionals;

    public List<BudgetKeyAdditionalEntity> getBudgetKeyAdditionals() {
        return budgetKeyAdditionals;
    }

     
     
    public List<ViewUserModuleEntity> getUserViewsByModule() {
        return userViewsByModule;
    }

    public void setUserViewsByModule(List<ViewUserModuleEntity> userViewsByModule) {
        this.userViewsByModule = userViewsByModule;
    }
    
    

    public boolean isUserCanChangePassword() {
        return userCanChangePassword;
    }

    public void setUserCanChangePassword(boolean userCanChangePassword) {
        this.userCanChangePassword = userCanChangePassword;
    }

    public String getUserDescription() {
        return userDescription != null ? userDescription.trim():userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public boolean isUserExpiresPassword() {
        return userExpiresPassword;
    }

    public void setUserExpiresPassword(boolean userExpiresPassword) {
        this.userExpiresPassword = userExpiresPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isUserIsEnabled() {
        return userIsEnabled;
    }

    public void setUserIsEnabled(boolean userIsEnabled) {
        this.userIsEnabled = userIsEnabled;
    }

    public Date getUserLastAccessDate() {
        return userLastAccessDate;
    }

    public void setUserLastAccessDate(Date userLastAccessDate) {
        this.userLastAccessDate = userLastAccessDate;
    }

    public Set<ObservationEntity> getObservationUser()
    {
        return observationUser;
    }

    public void setObservationUser(Set<ObservationEntity> observationUser)
    {
        this.observationUser = observationUser;
    }
    
    public String getUserName()
    {
        return userName != null ? userName.trim():userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPassword()
    {
        return userPassword != null ? userPassword.trim():userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public List<ProfileEntity> getUserProfile()
    {
        return userProfile;
    }

    public void setUserProfile(List<ProfileEntity> userProfile)
    {
        this.userProfile = userProfile;
    }

    public ColectiveEntity getUserColective() {
        return userColective;
    }

    public void setUserColective(ColectiveEntity userColective) {
        this.userColective = userColective;
    }

    @Override
    public int compareTo(UserEntity obj)
    {
        int result = -1;
        if (this.userId != null && obj.userId != null)
        {
            result = this.userId.compareTo(obj.userId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof UserEntity && this.userId != null
                && ((UserEntity) obj).userId != null)
        {
            result = this.userId.equals(
                    ((UserEntity) obj).userId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 73 * hash + (this.userId != null
                ? this.userId.hashCode() : 0);
        return hash;
    }

}
