package com.br.dureino.service;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class JobsService {

    @Schedule(hour = "*", minute = "*/59", persistent = false)
    public void jobReprocessarSituacaoPedido(){}


}
