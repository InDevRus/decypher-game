package org.indev.decyphergame.services.implementations;

import org.indev.decyphergame.dao.EncryptionDAO;
import org.indev.decyphergame.models.Encryption;
import org.indev.decyphergame.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
class GameServiceImplementation implements GameService {
    private EncryptionDAO encryptionDAO;

    @Override
    public Optional<Encryption> getUnclosedQuestion(String playerNickName) {
        return encryptionDAO.findUnclosedQuestion(playerNickName);
    }

    @Override
    public void assignRandomHint(String playerNickName) {
        var encryption = encryptionDAO.findUnclosedQuestion(playerNickName).orElseThrow();

        var hintPosition = new Random().nextInt(encryption.getQuestion().getWord().length());
        encryption.setHintPosition(hintPosition);

        encryptionDAO.merge(encryption);
    }


    @Autowired
    public void setEncryptionDAO(EncryptionDAO encryptionDAO) {
        this.encryptionDAO = encryptionDAO;
    }
}
