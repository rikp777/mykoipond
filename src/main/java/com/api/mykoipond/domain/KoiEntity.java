package com.api.mykoipond.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="kois")

//LOMBOK
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KoiEntity extends BaseEntity {

    @NotNull(message = "name is mandatory")
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters")
    private String name;

    @NotNull(message = "description is mandatory")
    @Size(min = 5, max = 250)
    private String description;

    @DateTimeFormat
    private Date birth;

    @NotNull(message = "description is mandatory")
    @Size(min = 1, max = 10)
    private String sex;

    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;

    //RELATIONS
        //many kois have one pond
        @ManyToOne(
                fetch = FetchType.EAGER,
                optional = false
        )
        @JoinColumn(name = "pond_id", nullable = false)
        private PondEntity pond;

        //A Koi has one SubBreed
        @ManyToOne()
        private SubBreedEntity subBreed;

        //A Koi has Many KoiParasite registers
        @OneToMany(mappedBy = "koi")
        private Set<KoiParasiteEntity> koiParasites = new HashSet<>();

}
