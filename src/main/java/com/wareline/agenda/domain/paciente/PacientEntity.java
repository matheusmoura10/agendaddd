package com.wareline.agenda.domain.paciente;

import java.util.Set;

import com.wareline.agenda.shared.entities.AggregateRoot;
import com.wareline.agenda.shared.validation.ValidationHandlerInterface;
import com.wareline.agenda.shared.vo.AddressVO;

import lombok.Getter;

@Getter
public class PacientEntity extends AggregateRoot<PacientID> {
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private Set<AddressVO> address;

    public PacientEntity(final String id, final String name, final String email, final String cpf, final String phone,
            final Set<AddressVO> address) {
        super(id != null ? PacientID.from(id) : PacientID.unique());
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
    }

    public static PacientEntity from(final String id, final String name, final String email, final String cpf,
            final String phone,
            final Set<AddressVO> address) {
        return new PacientEntity(id, name, email, cpf, phone, address);
    }

    public static PacientEntity from(final String name, final String email, final String cpf, final String phone,
            final Set<AddressVO> address) {
        return new PacientEntity(null, name, email, cpf, phone, address);
    }

    @Override
    public void validate(ValidationHandlerInterface handler) {
        new PacientValidator(this, handler).validate();
    }
}
