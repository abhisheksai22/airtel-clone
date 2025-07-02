package com.abhi.airtel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Router {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routerId;

    private String model;
    private String macAddress;
    private String ipAddress;
    private String firmwareVersion;
    private String status;

    @OneToMany(mappedBy = "router", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("router-band")
    @ToString.Exclude
    private List<Band> bands;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Router router = (Router) o;
        return getRouterId() != null && Objects.equals(getRouterId(), router.getRouterId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
