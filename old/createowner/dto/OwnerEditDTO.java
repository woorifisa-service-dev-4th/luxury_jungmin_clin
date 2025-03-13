package dev.spring.petclinic.old.createowner.dto;

import dev.spring.petclinic.old.createowner.entity.Owner;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
public class OwnerEditDTO {

    private Integer id;

    @NotEmpty(message = "❌ First name은 필수 입력 항목입니다.")
    private String firstName;

    @NotEmpty(message = "❌ Last name은 필수 입력 항목입니다.")
    private String lastName;

    @NotEmpty(message = "❌ Address는 비어 있을 수 없습니다.")
    private String address;

    @NotEmpty(message = "❌ City는 비어 있을 수 없습니다.")
    private String city;

    @NotEmpty(message = "❌ Telephone은 비어 있을 수 없습니다.")
    @Pattern(regexp = "\\d{9,11}$", message = "⚠ 전화번호는 숫자 9~11자리여야 합니다.")
    private String telephone;

    // ✅ Entity → DTO 변환 메서드
    public static OwnerEditDTO fromEntity(Owner owner) {
        return OwnerEditDTO.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .address(owner.getAddress())
                .city(owner.getCity())
                .telephone(owner.getTelephone())
                .build();
    }

    // ✅ DTO → Entity 변환 메서드
    public Owner toEntity() {
        return Owner.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .address(this.address)
                .city(this.city)
                .telephone(this.telephone)
                .build();
    }
}
