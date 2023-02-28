package com.adegomar.springbootlibrary.utils;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ExtractJWT {

    public static String payloadJWTExtraction(String token, String extraction){

        token.replace("Bearer ", "");

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        String[] entries = payload.split(",");
        Map<String, String> map = new HashMap<String, String>();

        for(String entry : entries){
            String[] keyValue = entry.split(":");
            if(keyValue[0].equals(extraction)){
                int remove = 1;
                if(keyValue[1].endsWith("}")){
                    remove = 2;
                }
                keyValue[1] = keyValue[1].substring(0, keyValue[1].length() - remove);
                keyValue[1] = keyValue[1].substring(1);

                map.put(keyValue[0], keyValue[1]);
            }
        }
        if(map.containsKey(extraction)){
            return map.get(extraction);
        }

        return null;
    }
}

/*
Mamy nasz extractJWT gdzie mamy mieć nasz public static string payloadJWTExtraction, gdzie będziemy przepuszczać "in" nasz token. Wywalimy "Bearer" z
tokena i zostawiamy to jako pusty string. chunks łamie nasz token na 3 różne chuki- header, payload i signature. Base64 robi dekodowanie chunków.
Dekodujemy tylko payload, który jest drugim elementem array. Potem dzielimy payload po przecinkach, żeby dostać każdą informację w payloadzie.

Robimy map, bo chcemy keyValue pair.

Przepuszczamy przez każde entries, dopóki nie znajdziemy wartości sub, która jest emailem. Dodajemy to jako key, a wtedy chcemy ewentualnie dodać
ending jako wartość, ale chcemy  się upewnić, że nie mamy wszystkich 'extra' czyli np }. Chcemy dodać tylko value, którą może być email.

Na końcu- jeżeli MAP ma containsKey, to niech zwróci value, która to email, a jak nie, to zwróci null.
*/
