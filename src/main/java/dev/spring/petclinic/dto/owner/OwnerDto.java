package dev.spring.petclinic.dto.owner;

import dev.spring.petclinic.model.owner.Owner;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter // ❗ 불변 객체 유지 (Setter 없음)
@Builder
public class OwnerDto {

    private final Integer id;

    @NotEmpty(message = "❌ First name은 필수 입력 항목입니다.")
    private final String firstName;

    @NotEmpty(message = "❌ Last name은 필수 입력 항목입니다.")
    private final String lastName;

    @NotEmpty(message = "❌ Address는 비어 있을 수 없습니다.")
    private final String address;

    @NotEmpty(message = "❌ City는 비어 있을 수 없습니다.")
    private final String city;

    @NotEmpty(message = "❌ Telephone은 비어 있을 수 없습니다.")
    @Pattern(regexp = "\\d{9,11}$", message = "⚠ 전화번호는 숫자 9~11자리여야 합니다.")
    private final String telephone;

    @ConstructorProperties({ "id", "firstName", "lastName", "address", "city", "telephone" })
    public OwnerDto(Integer id, String firstName, String lastName, String address, String city, String telephone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    // ❗ withId() 역할을 하는 메서드 추가
    public OwnerDto copyWithId(Integer id) {
        return new OwnerDto(id, this.firstName, this.lastName, this.address, this.city, this.telephone);
    }

    // Entity → DTO 변환
    public static OwnerDto fromEntity(Owner owner) {
        return new OwnerDto(
                owner.getId(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getAddress(),
                owner.getCity(),
                owner.getTelephone());
    }

    // DTO → Entity 변환
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
