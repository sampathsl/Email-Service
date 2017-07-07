/**
 * Created by SAMPATH on 07/01/2017.
 */

package com.lyke.email.service.adapter;

import com.ecwid.mailchimp.MailChimpClient;
import com.ecwid.mailchimp.MailChimpObject;
import com.ecwid.mailchimp.method.v1_3.campaign.CampaignCreateMethod;
import com.ecwid.mailchimp.method.v1_3.campaign.CampaignSendNowMethod;
import com.ecwid.mailchimp.method.v1_3.campaign.CampaignType;
import com.ecwid.mailchimp.method.v1_3.list.ListsMethod;
import com.ecwid.mailchimp.method.v1_3.list.ListsResult;
import com.google.gson.Gson;
import com.lyke.email.service.domain.EmailData;
import com.lyke.email.service.util.EmailContent;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author SAMPATH
 * Implement the ManDrill email service provider
 */

public class ManDrillService implements EmailServiceProvider {

	private static final Logger logger = LogManager.getLogger(ManDrillService.class);

    String API_KEY = ""; //TODO add key here

	@Override
	public boolean sendEmail(EmailData email) {
		try {

			MailChimpClient mailChimpClient = new MailChimpClient();
			ListsMethod listsMethod = new ListsMethod();
			listsMethod.apikey = API_KEY;
			ListsResult listsResult = mailChimpClient.execute(listsMethod);
			// ListInformation data = listsResult.data.get(0);

			CampaignCreateMethod campaignCreateMethod = new CampaignCreateMethod();
			campaignCreateMethod.apikey = API_KEY;
			campaignCreateMethod.type = CampaignType.regular;
			MailChimpObject options = new MailChimpObject();

			campaignCreateMethod.options = new MailChimpObject();
			options.put("list_id", "");//TODO add list id here
			options.put("subject", email.getSubject());
			options.put("from_email", "any email address");
			options.put("from_name", "TODO TEST");
			options.put("authenticate", true);
			options.put("title", "Test Work");
			options.put("tracking", "");

			options.put("to", String.join(",", String.join(",", email.getTo())));

			List<String> ccAndBcc = email.getCc();
			ccAndBcc.addAll(email.getBcc());
			if (ccAndBcc.size() > 0) {
				options.put("bcc_address", String.join(",", String.join(",", ccAndBcc)));
			}

			campaignCreateMethod.options = options;

			MailChimpObject content = new MailChimpObject();

			if (EmailContent.getEmailContentType(email.getMessage()).equalsIgnoreCase("text/plain")) {
				content.put("html", email.getMessage());
			} else if (EmailContent.getEmailContentType(email.getMessage()).equalsIgnoreCase("text/html")) {
				content.put("plain_text", email.getMessage());
			}

			campaignCreateMethod.content = content;
			Gson gson = new Gson();
			String responseString = gson.toJson(content);
			String campaignId = mailChimpClient.execute(campaignCreateMethod);

			CampaignSendNowMethod campaignSendNowMethod = new CampaignSendNowMethod();
			campaignSendNowMethod.apikey = API_KEY;
			campaignSendNowMethod.cid = campaignId;
			Boolean status = mailChimpClient.execute(campaignSendNowMethod);

			if (status != null && !status) {
				return false;
			}

			logger.info("ManDrill SEND EMAIL END");

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return false;
		}
		return true;
	}

}