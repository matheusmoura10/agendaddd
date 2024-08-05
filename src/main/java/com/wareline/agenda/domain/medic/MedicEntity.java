package com.wareline.agenda.domain.medic;

import java.util.List;

import com.wareline.agenda.domain.speciality.SpecialityEntity;
import com.wareline.agenda.shared.entities.AggregateRoot;
import com.wareline.agenda.shared.validation.ValidationHandlerInterface;
import com.wareline.agenda.shared.vo.AddressVO;

import lombok.Getter;

@Getter
public class MedicEntity extends AggregateRoot<MedicID> {

    private String name;
    private String crm;
    private String phone;
    private String email;
    private List<SpecialityEntity> specialities;
    private AddressVO address;

    public MedicEntity(String id, String name, String crm, String phone, String email,
            List<SpecialityEntity> specialities,
            AddressVO address) {
        super(id != null ? MedicID.from(id) : MedicID.unique());
        this.name = name;
        this.crm = crm;
        this.phone = phone;
        this.email = email;
        this.specialities = specialities;
        this.address = address;
    }

    @Override
    public void validate(ValidationHandlerInterface handler) {
        new MedicValidator(this, handler).validate();
    }

}
