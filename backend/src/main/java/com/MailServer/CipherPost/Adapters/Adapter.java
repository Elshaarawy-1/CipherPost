package com.MailServer.CipherPost.Adapters;

public interface Adapter<S,T> {
    public T toDto(S source);
}
