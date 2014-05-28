/*******************************************************************************
 * Copyright 2014 Time Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.timeInc.dps.publish.response;

import java.util.List;

import com.timeInc.dps.publish.request.config.PublishConfig;

/**
 * Response that is created as a result of making 
 * a PublishStatus request.
 * 
 * Contains request Id, details, status, and progress for each request.
 * You can use the requestId to cancel or delete the publish request. 
 * 
 * See Adobe Publishing API for more information.
 *
 */
public class PublishingStatus extends Result {
	private List<Response> requests;
	
	/**
	 * Gets the publish status.
	 *
	 * @return the publish status
	 */
	public List<Response> getPublishStatus() {
		return requests;
	}

	/**
	 * The Class Response.
	 */
	public static class Response {
		
		/**
		 * The Enum State.
		 */
		public enum State {	
			PENDING("pending"),
			STARTED("started"),
			COMPLETED("completed"),
			CANCELED("canceled"),
			FAILED("failed");

			private final String shortName;

			private State(String shortName) {
				this.shortName = shortName;
			}

			/* (non-Javadoc)
			 * @see java.lang.Enum#toString()
			 */
			public String toString() {
				return shortName;
			}
		}

		private String requestId;
		private State jobStatus;
		private String magazineTitle;
		private String issueTitle;
		private String adcIssueId;
		private PublishConfig.State state;
		private String productId;
		private Boolean retail;
		private String createTime;
		private String startTime;
		private String parentRequestId;
		
		private Long endTime;
		private Integer jobTasksTotal;
		private Integer jobTasksCompleted;
		private Long onSaleDate;
		
		private Boolean updateContents;

		/**
		 * Gets the request id.
		 *
		 * @return the request id
		 */
		public String getRequestId() {
			return requestId;
		}

		/**
		 * Gets the job status.
		 *
		 * @return the job status
		 */
		public State getJobStatus() {
			return jobStatus;
		}

		/**
		 * Gets the magazine title.
		 *
		 * @return the magazine title
		 */
		public String getMagazineTitle() {
			return magazineTitle;
		}

		/**
		 * Gets the issue title.
		 *
		 * @return the issue title
		 */
		public String getIssueTitle() {
			return issueTitle;
		}

		/**
		 * Gets the adc issue id.
		 *
		 * @return the adc issue id
		 */
		public String getAdcIssueId() {
			return adcIssueId;
		}

		/**
		 * Gets the state.
		 *
		 * @return the state
		 */
		public PublishConfig.State getState() {
			return state;
		}

		/**
		 * Gets the product id.
		 *
		 * @return the product id
		 */
		public String getProductId() {
			return productId;
		}

		/**
		 * Gets the retail.
		 *
		 * @return the retail
		 */
		public Boolean getRetail() {
			return retail;
		}

		/**
		 * Gets the creates the time.
		 *
		 * @return the creates the time
		 */
		public String getCreateTime() {
			return createTime;
		}

		/**
		 * Gets the start time.
		 *
		 * @return the start time
		 */
		public String getStartTime() {
			return startTime;
		}

		/**
		 * Gets the parent request id.
		 *
		 * @return the parent request id
		 */
		public String getParentRequestId() {
			return parentRequestId;
		}

		/**
		 * Gets the end time.
		 *
		 * @return the end time
		 */
		public Long getEndTime() {
			return endTime;
		}

		/**
		 * Gets the job tasks total.
		 *
		 * @return the job tasks total
		 */
		public Integer getJobTasksTotal() {
			return jobTasksTotal;
		}

		/**
		 * Gets the job tasks completed.
		 *
		 * @return the job tasks completed
		 */
		public Integer getJobTasksCompleted() {
			return jobTasksCompleted;
		}

		/**
		 * Gets the on sale date.
		 *
		 * @return the on sale date
		 */
		public Long getOnSaleDate() {
			return onSaleDate;
		}

		/**
		 * Gets the update contents.
		 *
		 * @return the update contents
		 */
		public Boolean getUpdateContents() {
			return updateContents;
		}
	}
}
