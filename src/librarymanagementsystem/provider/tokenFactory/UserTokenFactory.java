package de.hhn.it.pp.components.librarymanagementsystem.provider.tokenFactory;

import de.hhn.it.pp.components.librarymanagementsystem.AccountDescriptor;

public class UserTokenFactory extends AbstractTokenFactory {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(UserTokenFactory.class);

    /**
     * Must generate a unique string which cannot reproduce the original data, e.g. by using a hash
     * function.
     * Same object data should produces same tokens.
     *
     * @param object input to the mechanism to generate always the same token based on the same object
     * @return resulting string
     */
    @Override
    protected String generateTokenString(final Object object) {
        // For test reasons we want that users with the same email get always the same token
        if (object instanceof AccountDescriptor) {
            AccountDescriptor accountDescriptor = (AccountDescriptor) object;
            return "email-" + accountDescriptor.getPerson().getEMail();
        }
        return object.toString();
    }
}

