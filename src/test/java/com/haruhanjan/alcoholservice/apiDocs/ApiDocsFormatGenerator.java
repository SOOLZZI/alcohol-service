package com.haruhanjan.alcoholservice.apiDocs;

import com.mysql.cj.x.protobuf.Mysqlx;
import org.springframework.restdocs.snippet.Attributes;
public interface ApiDocsFormatGenerator {


    static Attributes.Attribute boardPostType() {
        return key("format").value(BoardPostType.NOTICE + ":" + BoardPostType.NOTICE.getDescription());
    }
}
