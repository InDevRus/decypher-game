package org.indev.decyphergame.services.implementations;

import org.indev.decyphergame.dao.EncryptionDAO;
import org.indev.decyphergame.dao.PlayerDAO;
import org.indev.decyphergame.logic.cyphers.Encrypter;
import org.indev.decyphergame.models.Encryption;
import org.indev.decyphergame.models.Question;
import org.indev.decyphergame.models.values.CypherType;
import org.indev.decyphergame.services.EncrypterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.function.Function;

abstract class DefaultEncrypterService implements EncrypterService {
    private EncryptionDAO encryptionDAO;
    private PlayerDAO playerDAO;

    abstract Encrypter getEncrypter();

    abstract CypherType getCypherType();

    @Override
    public final Optional<Encryption> chooseQuestion(String playerNickName) {
        var player = playerDAO.findByNickName(playerNickName).orElseThrow();
        Function<Question, Encryption> encryptQuestion = question -> {
            var encryption = new Encryption();

            encryption.setQuestion(question);
            encryption.setCypher(getEncrypter().encrypt(question.getWord()));

            encryption.setPlayer(player);
            encryption.setCypherType(getCypherType());
            encryptionDAO.persist(encryption);

            return encryption;
        };

        return encryptionDAO.findUnclosedQuestion(playerNickName)
                .or(() -> encryptionDAO.chooseUnansweredQuestion(playerNickName).map(encryptQuestion));
    }

    @Autowired
    void setEncryptionDAO(EncryptionDAO encryptionDAO) {
        this.encryptionDAO = encryptionDAO;
    }

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
}
