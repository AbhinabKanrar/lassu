/**
 * 
 */
package com.lassu.security.dao;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import com.lassu.security.common.domain.AuditData;
import com.lassu.security.common.domain.User;
import com.lassu.security.common.util.CommonConstant;

/**
 * @author abhinab
 *
 */
public interface AuthAuditDao {

	@Retryable(maxAttempts=CommonConstant.DB_RETRY_COUNT,value=DataAccessResourceFailureException.class,backoff=@Backoff(delay = CommonConstant.DB_RETRY_DELAY))
	User getUserByUsername(String username);
	
	@Retryable(maxAttempts=CommonConstant.DB_RETRY_COUNT,value=DataAccessResourceFailureException.class,backoff=@Backoff(delay = CommonConstant.DB_RETRY_DELAY))
	User update(AuditData auditData);
	
}
