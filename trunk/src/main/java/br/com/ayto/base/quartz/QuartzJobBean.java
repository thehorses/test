package br.com.ayto.base.quartz;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class QuartzJobBean extends org.springframework.scheduling.quartz.QuartzJobBean implements Job {

	public abstract void executeIssoSeLiberado(JobExecutionContext jec) throws Exception;

	@Override
	protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
		try {
			getLog().debug("Job iniciado");
			executeIssoSeLiberado(jec);
			getLog().debug("Job concluido");
		} catch (Exception e) {
			getLog().error(ExceptionUtils.getStackTrace(e));
		}
	}

	public abstract Log getLog();

}
