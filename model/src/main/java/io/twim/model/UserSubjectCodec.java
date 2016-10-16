/*
 * TWIM Copyright (c) 2016
 *
 * This document is the property of TWIM, you cannot copy or reproduce this without authorization.
 */

package io.twim.model;

import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import com.datastax.driver.core.exceptions.InvalidTypeException;

import java.nio.ByteBuffer;


/**
 * Codec to parse UserSubject UDT within cassandra
 * <p>
 * Created by christmo on 7/10/16.
 */
public class UserSubjectCodec extends TypeCodec<UserSubject> {

    private final String ID = "id";
    private final String LOGIN = "login";
    private final String ROLES = "roles";

    private UserType userType;
    private TypeCodec<UDTValue> innerCodec;

    /**
     * Default Constructor
     *
     * @param innerCodec codec from cassandra
     * @param javaType   class UDT annotated
     */
    public UserSubjectCodec(TypeCodec<UDTValue> innerCodec, Class<UserSubject> javaType) {
        super(innerCodec.getCqlType(), javaType);
        this.innerCodec = innerCodec;
        this.userType = (UserType) innerCodec.getCqlType();
    }

    /**
     * Serialize with the codec
     *
     * @param value           Object UDT to serialize
     * @param protocolVersion version protocol of cassandra
     * @return ByteBuffer binary data needed for cassandra to persist within UDT field
     * @throws InvalidTypeException
     */
    @Override
    public ByteBuffer serialize(UserSubject value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return value == null ? null : innerCodec.serialize(toUDTValue(value), protocolVersion);
    }

    /**
     * Deserialize with the codec
     *
     * @param bytes           binary to convert in Object
     * @param protocolVersion version protocol of cassandra
     * @return UserSubject Object UDT to get the data
     * @throws InvalidTypeException
     */
    @Override
    public UserSubject deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return bytes == null ? null : toUserSubject(innerCodec.deserialize(bytes, protocolVersion));
    }

    @Override
    public UserSubject parse(String value) throws InvalidTypeException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String format(UserSubject value) throws InvalidTypeException {
        throw new UnsupportedOperationException();
    }

    protected UserSubject toUserSubject(UDTValue value) {
        UserSubject userSubject = new UserSubject();
        userSubject.setId(value.getString(ID));
        userSubject.setLogin(value.getString(LOGIN));
        userSubject.setRoles(value.getList(ROLES, String.class));

        return userSubject;
    }

    protected UDTValue toUDTValue(UserSubject value) {
        UDTValue udt = userType.newValue();
        udt.setString(LOGIN, value.getLogin());
        udt.setString(ID, value.getId());
        udt.setList(ROLES, value.getRoles());

        return udt;
    }

}
