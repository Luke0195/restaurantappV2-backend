package br.com.waiterapp.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String authority;
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name="user_fk", value = ConstraintMode.CONSTRAINT))
    private User user;
}
