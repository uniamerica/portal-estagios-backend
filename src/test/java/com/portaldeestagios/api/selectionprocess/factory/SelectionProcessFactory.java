package com.portaldeestagios.api.selectionprocess.factory;

import com.portaldeestagios.api.selectionprocess.SelectionProcessEntity;
import com.portaldeestagios.api.selectionprocess.SelectionProcessStatusEnum;

public class SelectionProcessFactory {

    public static SelectionProcessEntity buildDefautlWithoutId(){
        return SelectionProcessEntity.builder()
                .companyName("Prancing Pony")
                .title("The Fellowship of the Ring")
                .status(SelectionProcessStatusEnum.ABERTO)
                .build();
    }

    public static SelectionProcessEntity buildWithoutTitle(){
        return SelectionProcessEntity.builder()
                .companyName("Prancing Pony")
                .build();
    }

    public static SelectionProcessEntity buildWithoutCompanyName(){
        return SelectionProcessEntity.builder()
                .title("The Fellowship of the Ring")
                .build();
    }
}
