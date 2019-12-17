package org.indev.decyphergame.services.implementations;

import org.indev.decyphergame.logic.cyphers.Encrypter;
import org.indev.decyphergame.models.values.CypherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("dialPadService")
class DialPadServiceImplementation extends DefaultEncrypterService {
    private Encrypter encrypter;

    @Autowired
    @Qualifier("createDialPad")
    public void setEncrypter(Encrypter encrypter) {
        this.encrypter = encrypter;
    }

    @Override
    Encrypter getEncrypter() {
        return encrypter;
    }

    @Override
    CypherType getCypherType() {
        return CypherType.DIALPAD;
    }
}
