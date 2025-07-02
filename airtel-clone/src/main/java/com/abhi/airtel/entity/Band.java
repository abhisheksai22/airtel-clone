package com.abhi.airtel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bandId;
    private String bandName;
    private transient String bandPassword;

    @Enumerated(EnumType.STRING)
    private BandType bandType;

    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("band-device")
    @ToString.Exclude
    private List<Device> devices;

    @ManyToOne
    @JoinColumn(name = "router_id")
    @JsonBackReference("router-band")
    private Router router;

}
