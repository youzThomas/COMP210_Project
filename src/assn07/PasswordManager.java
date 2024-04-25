package assn07;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password321";
    private Account[] _passwords;
    private int _size;

    public PasswordManager() {
        _passwords = new Account[50];
        _size = 0;
    }

    private int hash(K key) {
        int hashcode = key.hashCode();
        return Math.abs(hashcode) % size();
    }




    // TODO: put
    @Override
    public void put(K key, V value) {
        int index = Math.abs(key.hashCode() % _passwords.length);
        Account<K, V> newAccount = new Account<>(key, value);

        if (_passwords[index] == null) {
            _passwords[index] = newAccount;
        } else {
            Account<K, V> current = _passwords[index];
            Account<K, V> prev = null;

            while (current != null) {
                if (current.getWebsite().equals(key)) {
                    current.setPassword(value);
                    return;
                }
                prev = current;
                current = current.getNext();
            }

            prev.setNext(newAccount);
        }

        _size++;
    }
    // TODO: get
    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode() % _passwords.length);
        Account<K, V> current = _passwords[index];

        while (current != null) {
            if (current.getWebsite().equals(key)) {
                return current.getPassword();
            }
            current = current.getNext();
        }

        return null;
    }

    // TODO: size
    @Override
    public int size() {
        return _size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Account<K, V> account : _passwords) {
            if (account == null) continue;

            Account<K, V> current = account;
            while (current != null) {
                keys.add(current.getWebsite());
                current = current.getNext();
            }
        }
        return keys;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int index = Math.abs(key.hashCode() % _passwords.length);
        Account<K, V> current = _passwords[index];
        Account<K, V> prev = null;

        while (current != null) {
            if (current.getWebsite().equals(key)) {
                if (prev == null) {
                    _passwords[index] = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                _size--;
                return current.getPassword();
            }
            prev = current;
            current = current.getNext();
        }

        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> duplicateKeys = new ArrayList<>();
        for (Account<K, V> account : _passwords) {
            if (account == null) continue;

            Account<K, V> current = account;
            while (current != null) {
                if (current.getPassword().equals(value)) {
                    duplicateKeys.add(current.getWebsite());
                }
                current = current.getNext();
            }
        }
        return duplicateKeys;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return MASTER_PASSWORD.equals(enteredPassword);
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        if (length < 4) {
            length = 4;
        }

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4


        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
